package com.roman.wallet.backend.config;

import com.roman.shared.domain.TokenEncoder;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.infrastructure.spring.ApiExceptionMiddleware;
import com.roman.wallet.backend.middleware.JwtHttpAuthMiddleware;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class WalletServerConfiguration {
    private final QueryBus queryBus;
    private final TokenEncoder tokenEncoder;
    private final RequestMappingHandlerMapping mapping;

    public WalletServerConfiguration(QueryBus queryBus, TokenEncoder tokenEncoder, RequestMappingHandlerMapping mapping) {
        this.queryBus = queryBus;
        this.tokenEncoder = tokenEncoder;
        this.mapping = mapping;
    }

    @Bean
    public FilterRegistrationBean<JwtHttpAuthMiddleware> jwtHttpAuthMiddleware() {
        FilterRegistrationBean<JwtHttpAuthMiddleware> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new JwtHttpAuthMiddleware(queryBus, tokenEncoder));
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ApiExceptionMiddleware> apiExceptionMiddleware() {
        FilterRegistrationBean<ApiExceptionMiddleware> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new ApiExceptionMiddleware(mapping));

        return registrationBean;
    }
}
