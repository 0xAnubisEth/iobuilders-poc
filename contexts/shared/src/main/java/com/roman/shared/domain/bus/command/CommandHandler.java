package com.roman.shared.domain.bus.command;

import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;

public interface CommandHandler<T extends Command> {
    void handle(T command) throws QueryHandlerExecutionError;
}
