package com.roman.wallet.transactions.infrastructure.persistence;

import com.roman.shared.infrastructure.hibernate.HibernateRepository;
import com.roman.wallet.transactions.domain.Transaction;
import com.roman.wallet.transactions.domain.TransactionRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Transactional("wallet-transaction_manager")
public class MySqlTransactionRepository extends HibernateRepository<Transaction> implements TransactionRepository {
    public MySqlTransactionRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Transaction.class);
    }

    @Override
    public void save(Transaction transaction) {
        persist(transaction);
    }

    @Override
    public List<Transaction> getAll(String accountId) {
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> transactionRoot = cq.from(Transaction.class);
        cq.where(cb.equal(transactionRoot.get("destination"), accountId));
        return sessionFactory.getCurrentSession().createQuery(cq).getResultList();
    }
}
