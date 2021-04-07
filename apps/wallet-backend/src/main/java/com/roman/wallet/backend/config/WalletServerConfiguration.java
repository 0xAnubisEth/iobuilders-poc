package com.roman.wallet.backend.config;

import com.roman.shared.domain.TokenEncoder;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.wallet.backend.middleware.JwtHttpAuthMiddleware;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletServerConfiguration {
    private final QueryBus queryBus;
    private final TokenEncoder tokenEncoder;

    public WalletServerConfiguration(QueryBus queryBus, TokenEncoder tokenEncoder) {
        this.queryBus = queryBus;
        this.tokenEncoder = tokenEncoder;
    }

    @Bean
    public FilterRegistrationBean<JwtHttpAuthMiddleware> jwtHttpAuthMiddleware() {
        FilterRegistrationBean<JwtHttpAuthMiddleware> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new JwtHttpAuthMiddleware(queryBus, tokenEncoder));
        registrationBean.addUrlPatterns("/wallet/*");

        return registrationBean;
    }
}
