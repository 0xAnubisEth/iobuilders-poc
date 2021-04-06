package com.roman.wallet.accounts.infrastructure.persistence;

import com.roman.shared.infrastructure.hibernate.HibernateRepository;
import com.roman.wallet.accounts.domain.Account;
import com.roman.wallet.accounts.domain.AccountRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("wallet-transaction_manager")
public class MySqlAccountRepository extends HibernateRepository<Account> implements AccountRepository {
    public MySqlAccountRepository(SessionFactory sessionFactory, Class<Account> aggregateClass) {
        super(sessionFactory, aggregateClass);
    }

    @Override
    public void save(Account account) {
        this.persist(account);
    }
}
