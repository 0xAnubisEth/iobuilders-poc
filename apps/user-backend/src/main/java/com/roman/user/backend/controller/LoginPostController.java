package com.roman.user.backend.controller;

import com.roman.shared.domain.DomainError;
import com.roman.shared.domain.bus.command.CommandBus;
import com.roman.shared.domain.bus.command.CommandHandlerExecutionError;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.shared.infrastructure.spring.ApiController;
import com.roman.shared.application.auth.UserAuthResponse;
import com.roman.user.auth.application.login.LoginAuthUserCommand;
import com.roman.user.auth.application.search_by_username.SearchAuthUserByUsernameQuery;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginPostController extends ApiController {

    public LoginPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<String> index(@RequestBody Body body) throws CommandHandlerExecutionError, QueryHandlerExecutionError {
        // Login
        dispatch(new LoginAuthUserCommand(body.username(), body.password()));

        // Get token
        UserAuthResponse response = ask(new SearchAuthUserByUsernameQuery(body.username()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", response.token());
        return ResponseEntity.ok().headers(headers).body("Ok");
    }

    private static class Body {
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
}
