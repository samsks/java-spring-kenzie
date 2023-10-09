package br.com.samuel.learningspring.controller;

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
    public ResponseEntity<User> createUser(@RequestBody final User userData){
        final User createdUser = userService.createUser(userData);

        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> readUsers(){
        final List<User> allUsers = userService.readUsers();

        return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }
}
