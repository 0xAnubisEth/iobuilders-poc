package com.roman.user.users;

import com.roman.shared.infrastructure.UnitTestCase;
import com.roman.user.shared.domain.PasswordEncoder;
import com.roman.user.users.domain.User;
import com.roman.user.users.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;

public class UsersModuleUnitTestCase extends UnitTestCase {
    protected UserRepository repository;
    protected PasswordEncoder passwordEncoder;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
    }

    public void shouldSearch(String username, User user) {
        Mockito.when(repository.findOneByUsername(username)).thenReturn(Optional.of(user));
    }

    public void shouldSearch(String username) {
        Mockito.when(repository.findOneByUsername(username)).thenReturn(Optional.empty());
    }

    public void shouldHaveSaved(User user) {
        Mockito.doNothing().when(repository).save(user);
    }

    public void shouldNotHaveSaved(User user) {
        Mockito.doThrow(new Exception()).when(repository).save(user);
    }

    public void shouldEncode(String text, String encoded) {
        Mockito.when(passwordEncoder.encode(text)).thenReturn(encoded);
    }

    public void shouldMatch(String text, String otherText, boolean result) {
        Mockito.when(passwordEncoder.match(text, otherText)).thenReturn(result);
    }

}
