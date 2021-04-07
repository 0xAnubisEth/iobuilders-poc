package com.roman.user.users.infrastructure.persistence;

import com.roman.shared.infrastructure.hibernate.HibernateRepository;
import com.roman.user.users.domain.User;
import com.roman.user.users.domain.UserRepository;
import com.roman.user.users.domain.UserUsername;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<User> findOneByUsername(String username) {
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.where(cb.equal(userRoot.get("username"), new UserUsername(username)));
        List<User> users = sessionFactory.getCurrentSession().createQuery(cq).getResultList();
        if (users.size() > 0) {
            return Optional.of(users.get(0));
        }
        return Optional.empty();
    }
}
