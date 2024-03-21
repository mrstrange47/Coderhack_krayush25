package com.crio.coderhack.service;

import com.crio.coderhack.dto.Username;
import com.crio.coderhack.entity.User;
import com.crio.coderhack.exceptions.ScoreOutOfRangeException;
import com.crio.coderhack.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    User addUser(Username username);
    List<User> findAllUsers();
    User getUserById(String userId) throws UserNotFoundException;
    User updateUserScore(String userId, int score) throws UserNotFoundException, ScoreOutOfRangeException;
    String deleteUserById(String userId) throws UserNotFoundException;

}
