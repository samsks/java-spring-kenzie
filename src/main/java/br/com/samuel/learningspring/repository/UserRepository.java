package br.com.samuel.learningspring.repository;

import br.com.samuel.learningspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(final String email);
    boolean existsUserByCpf(final String cpf);
}
