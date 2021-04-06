package com.roman.user.shared.infrastructure;

import com.roman.shared.infrastructure.hibernate.HibernateConfigurationFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

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

    @Bean("user-data_source")
    public DataSource dataSource() {
        return factory.dataSource(
                environment.getRequiredProperty("user.database.host"),
                Integer.valueOf(environment.getRequiredProperty("user.database.port")),
                environment.getRequiredProperty("user.database.name"),
                environment.getRequiredProperty("user.database.user"),
                environment.getRequiredProperty("user.database.password")
        );
    }
}
