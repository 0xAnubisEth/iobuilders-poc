package com.roman.wallet.transactions.application.search_all_by_user;

import com.roman.shared.domain.bus.query.Response;

import java.util.List;

public class TransactionsResponse implements Response {
    List<TransactionResponse> transactions;

    public TransactionsResponse(List<TransactionResponse> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionResponse> transactions() {
        return transactions;
    }
}
