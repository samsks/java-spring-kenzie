package br.com.samuel.learningspring.service;

import br.com.samuel.learningspring.dto.CreateDepositDTO;
import br.com.samuel.learningspring.dto.CreateUserDTO;
import br.com.samuel.learningspring.model.User;
import br.com.samuel.learningspring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(final CreateUserDTO userData){
        final User newUser = new User(userData.getName(), userData.getCpf(), userData.getEmail(), userData.getPassword(), userData.getType());

        return userRepository.save(newUser);
    }

    public List<User> readUsers(){
        return userRepository.findAll();
    }

    public User readUserById(final Long id) throws Exception{
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public User updateUser(final User userData, final Long id) throws Exception{
        final User foundUser = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        foundUser.setName(userData.getName());
        foundUser.setCpf(userData.getCpf());
        foundUser.setEmail(userData.getEmail());
        foundUser.setPassword(userData.getPassword());
        foundUser.setType(userData.getType());

        return userRepository.save(foundUser);
    }

    public void deleteUser(final Long id) throws Exception{
        final User foundUser = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        userRepository.delete(foundUser);
    }

    public User createDeposit(final CreateDepositDTO depositData, final Long id){
        final User foundUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        final float currentBalance = foundUser.getBalance();

        foundUser.setBalance(currentBalance + depositData.getValue());

        return userRepository.save(foundUser);
    }
}
