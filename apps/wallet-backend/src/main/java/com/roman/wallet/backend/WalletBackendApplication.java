package com.roman.wallet.backend;

import com.roman.wallet.backend.command.ConsumeRabbitMqDomainEventsCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(
        value = {"com.roman.shared", "com.roman.wallet", "com.roman.wallet.backend"}
)
public class WalletBackendApplication implements CommandLineRunner {
    private final ConsumeRabbitMqDomainEventsCommand rabbitMqCommand;

    public WalletBackendApplication(ConsumeRabbitMqDomainEventsCommand rabbitMqCommand) {
        this.rabbitMqCommand = rabbitMqCommand;
    }

    public static void main(String[] args) {
        SpringApplication.run(WalletBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.rabbitMqCommand.execute();
    }
}
