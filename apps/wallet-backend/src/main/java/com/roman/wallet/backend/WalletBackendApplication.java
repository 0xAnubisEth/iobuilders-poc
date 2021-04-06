package com.roman.wallet.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(
        value = {"com.roman.shared", "com.roman.wallet"}
)
public class WalletBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletBackendApplication.class, args);
    }
}
