package com.roman.wallet.users.domain;

public final class WalletUser {
    private final String id;
    private final String username;
    private final String name;
    private final String lastname;

    public WalletUser(String id, String username, String name, String lastname) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastname = lastname;
    }

    public String id() {
        return id;
    }

    public String username() {
        return username;
    }

    public String name() {
        return name;
    }

    public String lastname() {
        return lastname;
    }
}
