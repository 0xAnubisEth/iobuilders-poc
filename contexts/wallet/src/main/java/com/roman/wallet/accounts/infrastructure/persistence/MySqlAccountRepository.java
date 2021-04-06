package com.roman.wallet.accounts.infrastructure.persistence;

import com.roman.shared.domain.Identifier;
import com.roman.shared.infrastructure.hibernate.HibernateRepository;
import com.roman.wallet.accounts.domain.Account;
import com.roman.wallet.accounts.domain.AccountRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
@Transactional("wallet-transaction_manager")
public class MySqlAccountRepository extends HibernateRepository<Account> implements AccountRepository {
    public MySqlAccountRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Account.class);
    }

    @Override
    public void save(Account account) {
        this.persist(account);
    }

    @Override
    public Optional<Account> findByUserId(Identifier userId) {
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> accountRoot = cq.from(Account.class);
        cq.where(cb.equal(accountRoot.get("userId"), userId.value()));
        List<Account> accounts = sessionFactory.getCurrentSession().createQuery(cq).getResultList();
        if (accounts.size() > 0) {
            return Optional.of(accounts.get(0));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> findById(Identifier id) {
        return byId(id);
    }
}
