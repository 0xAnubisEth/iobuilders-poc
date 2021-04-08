package com.roman.user.users.application.search_by_username;

import com.roman.shared.domain.bus.query.QueryHandler;
import com.roman.user.users.application.UserResponse;
import com.roman.user.users.domain.User;
import org.springframework.stereotype.Service;

@Service
public class SearchByUsernameQueryHandler implements QueryHandler<SearchByUsernameQuery, UserResponse> {
    private final UserFinderByUsername finder;

    public SearchByUsernameQueryHandler(UserFinderByUsername finder) {
        this.finder = finder;
    }

    @Override
    public UserResponse handle(SearchByUsernameQuery query) {
        User user = finder.find(query.username());
        return new UserResponse(user.id().value(), user.username().value(), user.password().value(), user.name().value(), user.lastName().value());
    }
}
