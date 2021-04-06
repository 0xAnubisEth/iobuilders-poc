package com.roman.wallet.users.infrastructure.persistence;

import com.roman.shared.infrastructure.hibernate.HibernateRepository;
import com.roman.wallet.users.domain.WalletUser;
import com.roman.wallet.users.domain.WalletUserRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("wallet-transaction_manager")
public class MySqlUserRepository extends HibernateRepository<WalletUser> implements WalletUserRepository {
    public MySqlUserRepository(SessionFactory sessionFactory, Class<WalletUser> aggregateClass) {
        super(sessionFactory, aggregateClass);
    }

    @Override
    public void save(WalletUser user) {
        this.persist(user);
    }
}
