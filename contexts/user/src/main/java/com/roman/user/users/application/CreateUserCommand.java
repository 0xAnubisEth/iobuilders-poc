package com.roman.user.users.application;

import com.roman.shared.domain.bus.command.Command;

public class CreateUserCommand implements Command {
    private final String id;
    private final String username;
    private final String password;

    public CreateUserCommand(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String id() {
        return id;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }
}
