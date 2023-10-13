package br.com.samuel.learningspring.service;

import br.com.samuel.learningspring.dto.CreateTransactionDTO;
import br.com.samuel.learningspring.exception.AppException;
import br.com.samuel.learningspring.model.Transaction;
import br.com.samuel.learningspring.model.User;
import br.com.samuel.learningspring.repository.TransactionRepository;
import br.com.samuel.learningspring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository ;


    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction(final CreateTransactionDTO transactionData){
        final User foundPayerUser = userRepository.findById(transactionData.getPayer_id()).orElseThrow(() -> new AppException("payerNotFound", HttpStatus.NOT_FOUND));
        if (Objects.equals(foundPayerUser.getType(), "SELLER")) throw new AppException("invalidTransactionUserType", HttpStatus.FORBIDDEN);
        final User foundPayeeUser = userRepository.findById(transactionData.getPayee_id()).orElseThrow(() -> new AppException("payeeNotFound",  HttpStatus.NOT_FOUND));

        final float transactionValue = transactionData.getValue();

        final float payerCurrentBalance = foundPayerUser.getBalance();
        if (payerCurrentBalance < transactionValue) throw new AppException("balanceNotSufficient", HttpStatus.FORBIDDEN);

        final float payeeCurrentBalance = foundPayeeUser.getBalance();

        foundPayerUser.setBalance(payerCurrentBalance - transactionValue);
        foundPayeeUser.setBalance(payeeCurrentBalance + transactionValue);

        Transaction newTransaction = new Transaction(foundPayerUser, foundPayeeUser, transactionData.getValue());

        return transactionRepository.save(newTransaction);
    }

    public Transaction readTransactionById(final Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new AppException("Transaction not found",  HttpStatus.NOT_FOUND));

    }
}

