package com.crio.coderhack.service;

import com.crio.coderhack.dto.Username;
import com.crio.coderhack.entity.User;
import com.crio.coderhack.exceptions.UserNotFoundException;
import com.crio.coderhack.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl UserServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Test
    void findAllUsersTest(){
        User user = new User("test_userId","test_username",10,new ArrayList<>());
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<User>(Arrays.asList(user)));
        List<User> result = UserServiceImpl.findAllUsers();
        Assertions.assertNotNull(result);
        Assertions.assertSame(1,result.size());
    }

    @Test
    void findUserByIdTest() throws UserNotFoundException {
        User user = new User("test_userId","test_username",10,new ArrayList<>());
        Mockito.when(userRepository.findById("test_userId")).thenReturn(Optional.of(user));
        User result = UserServiceImpl.getUserById("test_userId");
        Assertions.assertNotNull(result);
        Assertions.assertSame(10,result.getScore());
    }

    @Test
    void findUserByIdTest1() throws UserNotFoundException {
        User user = new User("test_userId","test_username",10,new ArrayList<>());
        Mockito.when(userRepository.findById("test_userId")).thenReturn(Optional.of(user));
        try{
            User result = UserServiceImpl.getUserById("invalid_userId");
        }
        catch (Exception e){
            Assertions.assertSame(e.getMessage(), "User not found");
        }
    }

    @Test
    void addUserTest() throws UserNotFoundException {
        User user = new User("test_userId","test_username",0,new ArrayList<>());
        Mockito.lenient().when(userRepository.save(any())).thenReturn((user));
        User result = UserServiceImpl.addUser(new Username("test_username"));
        Assertions.assertNotNull(result);
        Assertions.assertSame(0,result.getScore());
    }

    @Test
    void deleteByIdTest() throws UserNotFoundException {
        User user = new User("test_userId","test_username",10,new ArrayList<>());
        Mockito.when(userRepository.findById("test_userId")).thenReturn(Optional.of(user));
        Mockito.doNothing().when(userRepository).deleteById("test_userId");
        String result = UserServiceImpl.deleteUserById("test_userId");
        Assertions.assertNotNull(result);
    }

    @Test
    void deleteByIdTest1() throws UserNotFoundException {
        User user = new User("test_userId","test_username",10,new ArrayList<>());
        Mockito.when(userRepository.findById("test_userId")).thenReturn(Optional.of(user));
        Mockito.doNothing().when(userRepository).deleteById("test_userId");
        try{
            String result = UserServiceImpl.deleteUserById("invalid_userId");
        }
        catch (Exception e){
            Assertions.assertSame(e.getMessage(),"User not found");
        }
    }

}
