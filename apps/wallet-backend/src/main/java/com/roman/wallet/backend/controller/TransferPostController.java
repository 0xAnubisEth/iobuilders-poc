package com.roman.wallet.backend.controller;

import com.roman.shared.domain.DomainError;
import com.roman.shared.domain.bus.command.CommandBus;
import com.roman.shared.domain.bus.command.CommandHandlerExecutionError;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.infrastructure.spring.ApiController;
import com.roman.wallet.transactions.application.transfer.TransferTransactionCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/wallet")
public class TransferPostController extends ApiController {

    public TransferPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> index(HttpServletRequest request, @RequestBody Body body) throws CommandHandlerExecutionError {
        String id = UUID.randomUUID().toString();
        String userId = request.getAttribute("authentication_user").toString();
        dispatch(new TransferTransactionCommand(id, userId, body.quantity(), body.concept(), body.destination()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private static class Body {
        private final Float quantity;
        private final String concept;
        private final String destination;

        public Body(Float quantity, String concept, String destination) {
            this.quantity = quantity;
            this.concept = concept;
            this.destination = destination;
        }

        public Float quantity() {
            return quantity;
        }

        public String concept() {
            return concept;
        }

        public String destination() {
            return destination;
        }
    }
}
