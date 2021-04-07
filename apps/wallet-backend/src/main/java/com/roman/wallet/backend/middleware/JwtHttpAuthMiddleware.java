package com.roman.wallet.backend.middleware;

import com.roman.shared.application.auth.SearchTokenQuery;
import com.roman.shared.application.auth.UserAuthResponse;
import com.roman.shared.domain.TokenEncoder;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class JwtHttpAuthMiddleware implements Filter {
    private final QueryBus queryBus;
    private final TokenEncoder tokenEncoder;

    public JwtHttpAuthMiddleware(QueryBus queryBus, TokenEncoder tokenEncoder) {
        this.queryBus = queryBus;
        this.tokenEncoder = tokenEncoder;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = ((HttpServletRequest) request).getHeader("Authorization");

        if (authorizationHeader == null || authorizationHeader.equals("")) {
            unauthorized(response);
        } else {
            try {
                HashMap<String, Object> body = tokenEncoder.decode(authorizationHeader);
                String id = (String) body.get("_id");

                UserAuthResponse user = queryBus.ask(new SearchTokenQuery(id));

                if (user.token().equals(authorizationHeader)) {
                    request.setAttribute("authentication_user", user);
                    chain.doFilter(request, response);
                } else {
                    unauthorized(response);
                }
            } catch (QueryHandlerExecutionError queryHandlerExecutionError) {
                queryHandlerExecutionError.printStackTrace();
                unauthorized(response);
            }
        }
    }

    public void unauthorized(ServletResponse response) {
        ((HttpServletResponse) response).setStatus(401);
    }
}
