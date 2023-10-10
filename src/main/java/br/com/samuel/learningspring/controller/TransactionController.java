package br.com.samuel.learningspring.controller;

import br.com.samuel.learningspring.dto.CreateTransactionDTO;
import br.com.samuel.learningspring.model.Transaction;
import br.com.samuel.learningspring.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody final CreateTransactionDTO transactionData){
        final Transaction createdTransaction = transactionService.createTransaction(transactionData);

        return new ResponseEntity<Transaction>(createdTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> readTransactionById(@PathVariable final Long id) throws Exception{
        final Transaction transaction = transactionService.readTransactionById(id);

        return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
    }
}
