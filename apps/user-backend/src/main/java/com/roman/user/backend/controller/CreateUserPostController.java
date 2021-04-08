package com.roman.user.backend.controller;

import com.roman.shared.domain.DomainError;
import com.roman.shared.domain.InvalidArgumentError;
import com.roman.shared.domain.bus.command.CommandBus;
import com.roman.shared.domain.bus.command.CommandHandlerExecutionError;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.infrastructure.spring.ApiController;
import com.roman.user.users.application.create.CreateUserCommand;
import com.roman.user.users.domain.UserAlreadyExistsError;
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
        return new HashMap<>() {{
            put(UserAlreadyExistsError.class, HttpStatus.CONFLICT);
            put(InvalidArgumentError.class, HttpStatus.BAD_REQUEST);
        }};
    }

    @PostMapping("/users")
    public ResponseEntity<String> index(@RequestBody Body body) throws CommandHandlerExecutionError {
        String id = UUID.randomUUID().toString();
        dispatch(new CreateUserCommand(id, body.username(), body.password(), body.name(), body.lastname()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private static class Body {
        private final String username;
        private final String password;
        private final String name;
        private final String lastname;

        public Body(String username, String password, String name, String lastname) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.lastname = lastname;
        }

        public String name() {
            return name;
        }

        public String lastname() {
            return lastname;
        }

        public String username() {
            return username;
        }

        public String password() {
            return password;
        }
    }
}
