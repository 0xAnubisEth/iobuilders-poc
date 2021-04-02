package com.roman.user.shared.infrastructure;

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
public class UserHibernateConfiguration {

    private final HibernateConfigurationFactory factory;
    private final Environment environment;

    public UserHibernateConfiguration(HibernateConfigurationFactory factory, Environment environment) {
        this.factory = factory;
        this.environment = environment;
    }

    @Bean("user-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() {
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("user-session_factory")
    public LocalSessionFactoryBean sessionFactory() {
        return factory.sessionFactory("user", dataSource());
    }

    @Bean("mooc-data_source")
    public DataSource dataSource() {
        return factory.dataSource(
                environment.getProperty("user.database.host"),
                Integer.valueOf(Objects.requireNonNull(environment.getProperty("user.database.port"))),
                environment.getProperty("user.database.name"),
                environment.getProperty("user.database.user"),
                environment.getProperty("user.database.password")
        );
    }
}
