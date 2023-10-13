package br.com.samuel.learningspring.service;

import br.com.samuel.learningspring.dto.CreateDepositDTO;
import br.com.samuel.learningspring.dto.CreateUserDTO;
import br.com.samuel.learningspring.model.User;

import java.util.List;

public interface UserService {

    public User createUser(final CreateUserDTO userData);

    public List<User> readUsers();

    public User readUserById(final Long id);

    public User updateUser(final CreateUserDTO userData, final Long id);

    public void deleteUser(final Long id);

    public User createDeposit(final CreateDepositDTO depositData, final Long id);
}
