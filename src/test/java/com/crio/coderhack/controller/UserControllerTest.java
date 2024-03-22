package com.crio.coderhack.controller;

import com.crio.coderhack.dto.Score;
import com.crio.coderhack.dto.Username;
import com.crio.coderhack.entity.User;
import com.crio.coderhack.exceptions.ScoreOutOfRangeException;
import com.crio.coderhack.exceptions.UserNotFoundException;
import com.crio.coderhack.repository.UserRepository;
import com.crio.coderhack.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void createUserTest(){
        User user = new User("test_userId","test_username",0,new ArrayList<>());
        Mockito.lenient().when(userService.addUser(new Username("test_username"))).thenReturn(user);
        User result = userController.createUser(new Username("test_username"));
        Assertions.assertNotNull(result);
        Assertions.assertSame(result.getScore(),0);
    }

    @Test
    public void getAllUserTest(){
        User user = new User("test_userId","test_username",0,new ArrayList<>());
        Mockito.lenient().when(userService.findAllUsers()).thenReturn(new ArrayList<>(Arrays.asList(user)));
        List<User> result  = userController.getAllUser();
        Assertions.assertNotNull(result);
        Assertions.assertSame(1,result.size());
    }

    @Test
    public void getUserByIdTest() throws UserNotFoundException {
        User user = new User("test_userId","test_username",0,new ArrayList<>());
        Mockito.lenient().when(userService.getUserById("test_userId")).thenReturn(user);
        ResponseEntity<Object> result = userController.getUserById("test_userId");
        Assertions.assertNotNull(result);
        Assertions.assertSame(result.getStatusCode(), HttpStatusCode.valueOf(200));
    }

    @Test
    public void getUserByIdTest1() throws UserNotFoundException {
        User user = new User("test_userId","test_username",0,new ArrayList<>());
        Mockito.lenient().when(userService.getUserById("test_userId")).thenReturn(user);
        try{
            ResponseEntity<Object> result = userController.getUserById("invalid_userId");
        }
        catch (Exception e){
            Assertions.assertSame(e.getMessage(),"User not found");
        }

    }

    @Test
    public void updateScoreTest() throws UserNotFoundException, ScoreOutOfRangeException {
        User user = new User("test_userId","test_username",0,new ArrayList<>());
        Mockito.lenient().when(userService.updateUserScore("test_userId",20)).thenReturn(user);
        ResponseEntity<Object> result = userController.updateUserScore("test_userId", new Score(20));
        Assertions.assertNotNull(result);
        Assertions.assertSame(result.getStatusCode(), HttpStatusCode.valueOf(200));
    }

    @Test
    public void updateScoreTest1() throws UserNotFoundException, ScoreOutOfRangeException {
        User user = new User("test_userId","test_username",0,new ArrayList<>());
        Mockito.lenient().when(userService.updateUserScore("test_userId",103)).thenThrow(new ScoreOutOfRangeException("Score must be in range 0 to 100"));
        try{
            ResponseEntity<Object> result = userController.updateUserScore("test_userId", new Score(103));
        }
        catch (Exception e){
            Assertions.assertSame(e.getMessage(),"Score must be in range 0 to 100");
        }
    }

    @Test
    public void deleteUserTest() throws UserNotFoundException {
        Mockito.lenient().when(userService.deleteUserById("test_userId")).thenReturn("deleted");
        ResponseEntity<String> result = userController.deleteUser("test_userId");
        Assertions.assertNotNull(result);
        Assertions.assertSame(result.getStatusCode(), HttpStatusCode.valueOf(200));
    }
}
