package br.com.samuel.learningspring.service;

import br.com.samuel.learningspring.dto.CreateTransactionDTO;
import br.com.samuel.learningspring.model.Transaction;
import br.com.samuel.learningspring.model.User;
import br.com.samuel.learningspring.repository.TransactionRepository;
import br.com.samuel.learningspring.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository ;


    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction(final CreateTransactionDTO transactionData){
        final User foundPayerUser = userRepository.findById(transactionData.getPayer_id()).orElseThrow(() -> new RuntimeException("Payer not found"));
        final User foundPayeeUser = userRepository.findById(transactionData.getPayee_id()).orElseThrow(() -> new RuntimeException("Payee not found"));

        final float payerCurrentBalance = foundPayerUser.getBalance();
        final float payeeCurrentBalance = foundPayeeUser.getBalance();

        foundPayerUser.setBalance(payerCurrentBalance - transactionData.getValue());
        foundPayeeUser.setBalance(payeeCurrentBalance + transactionData.getValue());

        Transaction newTransaction = new Transaction(foundPayerUser, foundPayeeUser, transactionData.getValue());

        return transactionRepository.save(newTransaction);
    }

    public Transaction readTransactionById(final Long id) throws Exception{
        return transactionRepository.findById(id).orElseThrow(() -> new Exception("Transaction not found"));

    }
}

