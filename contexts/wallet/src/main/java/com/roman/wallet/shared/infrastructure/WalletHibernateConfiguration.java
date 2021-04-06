package com.roman.wallet.shared.infrastructure;

import com.roman.shared.infrastructure.hibernate.HibernateConfigurationFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
public class WalletHibernateConfiguration {
    private final HibernateConfigurationFactory factory;
    private final Environment environment;

    public WalletHibernateConfiguration(HibernateConfigurationFactory factory, Environment environment) {
        this.factory = factory;
        this.environment = environment;
    }

    @Bean("wallet-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() {
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("wallet-session_factory")
    public LocalSessionFactoryBean sessionFactory() {
        return factory.sessionFactory("wallet", dataSource());
    }

    @Bean("wallet-data_source")
    public DataSource dataSource() {
        return factory.dataSource(
                environment.getProperty("wallet.database.host"),
                Integer.valueOf(Objects.requireNonNull(environment.getProperty("wallet.database.port"))),
                environment.getProperty("wallet.database.name"),
                environment.getProperty("wallet.database.user"),
                environment.getProperty("wallet.database.password")
        );
    }
}