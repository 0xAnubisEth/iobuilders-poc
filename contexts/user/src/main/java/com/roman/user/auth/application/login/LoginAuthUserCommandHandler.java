package com.roman.user.auth.application.login;

import com.roman.shared.domain.bus.command.CommandHandler;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import org.springframework.stereotype.Service;

@Service
public class LoginAuthUserCommandHandler implements CommandHandler<LoginAuthUserCommand> {
    private final AuthUserLogger logger;

    public LoginAuthUserCommandHandler(AuthUserLogger logger) {
        this.logger = logger;
    }

    @Override
    public void handle(LoginAuthUserCommand command) throws QueryHandlerExecutionError {
        logger.login(command.username(), command.password());
    }
}
