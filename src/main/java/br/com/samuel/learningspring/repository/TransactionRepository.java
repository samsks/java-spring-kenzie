package br.com.samuel.learningspring.repository;

import br.com.samuel.learningspring.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
