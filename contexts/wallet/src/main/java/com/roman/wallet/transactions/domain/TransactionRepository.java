package com.roman.wallet.transactions.domain;

import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);
    List<Transaction> getAll(String accountId);
}
