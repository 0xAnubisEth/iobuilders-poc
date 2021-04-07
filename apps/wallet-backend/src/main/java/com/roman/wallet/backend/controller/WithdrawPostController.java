package com.roman.wallet.backend.controller;

import com.roman.shared.domain.DomainError;
import com.roman.shared.domain.bus.command.CommandBus;
import com.roman.shared.domain.bus.command.CommandHandlerExecutionError;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.infrastructure.spring.ApiController;
import com.roman.wallet.transactions.application.withdraw.WithdrawTransactionCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;

@RestController
public class WithdrawPostController extends ApiController {

    public WithdrawPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }

    @PostMapping("/wallet/withdraw")
    public ResponseEntity<String> index(HttpServletRequest request, @RequestBody Body body) throws CommandHandlerExecutionError {
        String id = UUID.randomUUID().toString();
        String userId = request.getAttribute("authentication_user").toString();
        dispatch(new WithdrawTransactionCommand(id, userId, body.quantity(), body.concept()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private static class Body {
        private final Float quantity;
        private final String concept;

        public Body(Float quantity, String concept) {
            this.quantity = quantity;
            this.concept = concept;
        }

        public Float quantity() {
            return quantity;
        }

        public String concept() {
            return concept;
        }
    }
}
