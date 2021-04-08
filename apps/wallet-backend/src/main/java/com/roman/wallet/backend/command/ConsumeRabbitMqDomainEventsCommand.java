package com.roman.wallet.backend.command;

import com.roman.shared.infrastructure.bus.event.rabbitmq.RabbitMqDomainEventsConsumer;
import org.springframework.stereotype.Service;

@Service
public final class ConsumeRabbitMqDomainEventsCommand {
    private final RabbitMqDomainEventsConsumer consumer;

    public ConsumeRabbitMqDomainEventsCommand(RabbitMqDomainEventsConsumer consumer) {
        this.consumer = consumer;
    }

    public void execute() {
        consumer.consume();
    }
}
