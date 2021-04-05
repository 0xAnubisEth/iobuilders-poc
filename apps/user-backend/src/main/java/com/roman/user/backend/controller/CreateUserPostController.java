package com.roman.user.backend.controller;

import com.roman.shared.domain.DomainError;
import com.roman.shared.domain.bus.command.CommandBus;
import com.roman.shared.domain.bus.command.CommandHandlerExecutionError;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.infrastructure.spring.ApiController;
import com.roman.user.users.application.CreateUserCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

@RestController
public class CreateUserPostController extends ApiController {

    public CreateUserPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }

    @PostMapping("/users")
    public ResponseEntity<String> index(@RequestBody Body body) throws CommandHandlerExecutionError {
        String id = UUID.randomUUID().toString();
        dispatch(new CreateUserCommand(id, body.username(), body.password()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    static class Body {
        private final String username;
        private final String password;

        public Body(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String username() {
            return username;
        }

        public String password() {
            return password;
        }
    }

    static class Response {
        private final String id;

        public Response(String id) {
            this.id = id;
        }
    }
}
