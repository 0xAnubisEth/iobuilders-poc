package com.roman.user.users.application.create;

import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.user.users.UsersModuleUnitTestCase;
import com.roman.user.users.domain.User;
import com.roman.user.users.domain.UserMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserCreatorShould extends UsersModuleUnitTestCase {
    private UserCreator creator;

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();

        creator = new UserCreator(repository, eventBus, passwordEncoder, queryBus);
    }

    @Test
    void save_a_valid_user() throws QueryHandlerExecutionError {
        User user = UserMother.random();

        shouldHaveSaved(user);

        creator.create(user.id().value(), user.username().value(), user.password().value(), user.name().value(), user.lastName().value());
    }

    @Test
    void throw_an_exception_when_the_infrastructure_fail() {
        assertThrows(Exception.class, () -> {
            User user = UserMother.random();

            shouldNotHaveSaved(user);

            creator.create(user.id().value(), user.username().value(), user.password().value(), user.name().value(), user.lastName().value());
        });
    }

}