package com.roman.user.auth.application.login;

import com.roman.shared.domain.bus.command.Command;

public class LoginAuthUserCommand implements Command {
    private final String username;
    private final String password;

    public LoginAuthUserCommand(String username, String password) {
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
