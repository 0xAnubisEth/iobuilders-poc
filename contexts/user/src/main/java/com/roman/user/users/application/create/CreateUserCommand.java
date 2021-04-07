package com.roman.user.users.application.create;

import com.roman.shared.domain.bus.command.Command;

public class CreateUserCommand implements Command {
    private final String id;
    private final String username;
    private final String password;
    private final String name;
    private final String lastname;

    public CreateUserCommand(String id, String username, String password, String name, String lastname) {
        this.id = id;
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
