package com.crio.coderhack.controller;

import com.crio.coderhack.dto.Score;
import com.crio.coderhack.dto.Username;
import com.crio.coderhack.entity.User;
import com.crio.coderhack.exceptions.ScoreOutOfRangeException;
import com.crio.coderhack.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crio.coderhack.service.UserService;

import java.util.List;

//static final String USER_API_ENDPOINT = "/user";

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

//  POST /users
    @PostMapping
    public User createUser(@RequestBody Username username){
        return userService.addUser(username);
    }

//  GET /users
    @GetMapping
    public List<User> getAllUser(){
        return userService.findAllUsers();
    }

//  GET /users/{userId}
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable String userId){
        User usr = null;
        try{
            usr = userService.getUserById(userId);
        }
        catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(usr);
    }

//  PUT /users/{userId}
    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUserScore(@PathVariable("userId") String userId, @RequestBody Score score){
        User usr = null;
        try{
            usr = userService.updateUserScore(userId, score.getScore());
        }
        catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (ScoreOutOfRangeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(usr);
    }

//  DELETE /users/{userId}
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) throws UserNotFoundException {
        String msg = "";
        try{
            msg = userService.deleteUserById(userId);
        }
        catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(msg);
    }
}
