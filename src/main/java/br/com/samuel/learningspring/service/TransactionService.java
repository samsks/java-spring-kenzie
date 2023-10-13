package br.com.samuel.learningspring.service;

import br.com.samuel.learningspring.dto.CreateTransactionDTO;
import br.com.samuel.learningspring.model.Transaction;

public interface TransactionService {

    public Transaction createTransaction(final CreateTransactionDTO transactionData);

    public Transaction readTransactionById(final Long id);
}

