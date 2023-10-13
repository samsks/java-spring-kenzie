package br.com.samuel.learningspring.service.impl;

import br.com.samuel.learningspring.dto.CreateDepositDTO;
import br.com.samuel.learningspring.dto.CreateUserDTO;
import br.com.samuel.learningspring.exception.AppException;
import br.com.samuel.learningspring.model.User;
import br.com.samuel.learningspring.repository.UserRepository;
import br.com.samuel.learningspring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private void checkEmailAndCPF(final CreateUserDTO userData){
        if (userRepository.existsUserByEmail(userData.getEmail())) throw new AppException("emailAlreadyExists", HttpStatus.CONFLICT);
        if (userRepository.existsUserByCpf(userData.getCpf())) throw new AppException("cpfAlreadyExists", HttpStatus.CONFLICT);
    }

    public User createUser(final CreateUserDTO userData){

        checkEmailAndCPF(userData);

        final User newUser = new User(userData.getName(), userData.getCpf(), userData.getEmail(), userData.getPassword(), userData.getType());

        return userRepository.save(newUser);
    }

    public List<User> readUsers(){
        return userRepository.findAll();
    }

    public User readUserById(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException("userNotFound",  HttpStatus.NOT_FOUND));
    }

    public User updateUser(final CreateUserDTO userData, final Long id) {
        checkEmailAndCPF(userData);

        final User foundUser = userRepository.findById(id).orElseThrow(() -> new AppException("userNotFound",  HttpStatus.NOT_FOUND));

        foundUser.setName(userData.getName());
        foundUser.setCpf(userData.getCpf());
        foundUser.setEmail(userData.getEmail());
        foundUser.setPassword(userData.getPassword());
        foundUser.setType(userData.getType());

        return userRepository.save(foundUser);
    }

    public void deleteUser(final Long id) {
        final User foundUser = userRepository.findById(id).orElseThrow(() -> new AppException("userNotFound",  HttpStatus.NOT_FOUND));
        userRepository.delete(foundUser);
    }

    public User createDeposit(final CreateDepositDTO depositData, final Long id){
        final User foundUser = userRepository.findById(id).orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));

        final float currentBalance = foundUser.getBalance();

        foundUser.setBalance(currentBalance + depositData.getValue());

        return userRepository.save(foundUser);
    }
}
