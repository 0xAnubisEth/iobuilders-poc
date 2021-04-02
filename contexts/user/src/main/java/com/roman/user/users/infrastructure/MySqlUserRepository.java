package com.roman.user.users.infrastructure;

import com.roman.shared.infrastructure.hibernate.HibernateRepository;
import com.roman.user.users.domain.User;
import com.roman.user.users.domain.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("user-transaction_manager")
public class MySqlUserRepository extends HibernateRepository<User> implements UserRepository {
    public MySqlUserRepository(@Qualifier("user-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    @Override
    public void save(User user) {
        persist(user);
    }
}
