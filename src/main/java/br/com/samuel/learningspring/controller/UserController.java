package br.com.samuel.learningspring.controller;

import br.com.samuel.learningspring.dto.CreateDepositDTO;
import br.com.samuel.learningspring.dto.CreateUserDTO;
import br.com.samuel.learningspring.model.User;
import br.com.samuel.learningspring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("${api.version}/users")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody final CreateUserDTO userData){
        final User createdUser = userService.createUser(userData);

        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> readUsers(){
        final List<User> allUsers = userService.readUsers();

        return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> readUserById(@PathVariable final Long id) throws Exception{
        final User user = userService.readUserById(id);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody final User userData, @PathVariable final Long id) throws Exception{
        final User updatedUser = userService.updateUser(userData, id);

        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final Long id) throws Exception{
        userService.deleteUser(id);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<User> createDeposit(@RequestBody final CreateDepositDTO depositData, @PathVariable final Long id) throws Exception{
        final User updatedUser = userService.createDeposit(depositData, id);

        return new ResponseEntity<User>(updatedUser, HttpStatus.CREATED);
    }
}
