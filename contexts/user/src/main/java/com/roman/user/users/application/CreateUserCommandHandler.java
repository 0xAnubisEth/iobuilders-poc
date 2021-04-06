package com.roman.user.users.application;

import com.roman.shared.domain.bus.command.CommandHandler;
import org.springframework.stereotype.Service;

@Service
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {
    private final UserCreator creator;

    public CreateUserCommandHandler(UserCreator creator) {
        this.creator = creator;
    }

    @Override
    public void handle(CreateUserCommand command) {
        this.creator.create(command.id(), command.username(), command.password(), command.name(), command.lastname());
    }
}
