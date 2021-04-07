package com.roman.wallet.backend.controller;

import com.roman.shared.domain.DomainError;
import com.roman.shared.domain.bus.command.CommandBus;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.shared.infrastructure.spring.ApiController;
import com.roman.wallet.transactions.application.search_all_by_user.SearchAllByUserQuery;
import com.roman.wallet.transactions.application.search_all_by_user.TransactionsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wallet")
public class TransactionsGetController extends ApiController {

    public TransactionsGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<Response> index(HttpServletRequest request) throws QueryHandlerExecutionError {
        String userId = request.getAttribute("authentication_user").toString();
        TransactionsResponse transactions = ask(new SearchAllByUserQuery(userId));
        return ResponseEntity.ok().body(new Response(transactions.transactions().stream().map(transaction -> new ResponseItem(
                transaction.concept(),
                transaction.origin(),
                transaction.destination(),
                transaction.quantity(),
                transaction.type()
        )).collect(Collectors.toList())));
    }

    private static class Response {
        public List<ResponseItem> transactions;

        public Response(List<ResponseItem> transactions) {
            this.transactions = transactions;
        }
    }

    private static class ResponseItem {
        public String concept;
        public String origin;
        public String destination;
        public Float quantity;
        public String type;

        public ResponseItem(String concept, String origin, String destination, Float quantity, String type) {
            this.concept = concept;
            this.origin = origin;
            this.destination = destination;
            this.quantity = quantity;
            this.type = type;
        }
    }
}
