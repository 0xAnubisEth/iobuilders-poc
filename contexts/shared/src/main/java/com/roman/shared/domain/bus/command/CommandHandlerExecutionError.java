package com.roman.shared.domain.bus.command;

public class CommandHandlerExecutionError extends Exception {
    public CommandHandlerExecutionError(Throwable cause) {
        super(cause);
    }
}
