package com.crio.coderhack.service;

import com.crio.coderhack.constants.BadgeConstants;
import com.crio.coderhack.dto.Username;
import com.crio.coderhack.entity.User;
import com.crio.coderhack.exceptions.ScoreOutOfRangeException;
import com.crio.coderhack.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crio.coderhack.repository.UserRepository;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(Username username) {
        User user = new User(username.getUsername());
        user.setUserId(UUID.randomUUID().toString().split("-")[0]);
        user.setBadges(new ArrayList<>());
        user.setScore(0);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> result  = userRepository.findAll();
        Collections.sort(result , Comparator.comparingInt(User::getScore));
        Collections.reverse(result);
        return result;
    }

    @Override
    public User getUserById(String userId) throws UserNotFoundException {
        User usr = null;
        try{
            usr = userRepository.findById(userId).get();
        }
        catch (Exception e){
            throw new UserNotFoundException("User not found");
        }
        return usr;
    }

    @Override
    public User updateUserScore(String userId, int score) throws UserNotFoundException, ScoreOutOfRangeException {
        User usr = null;
        try{
            usr = userRepository.findById(userId).get();
        }
        catch (Exception e){
            throw new UserNotFoundException("User not found");
        }
        if(score < 0 || score > 100){
            throw new ScoreOutOfRangeException("Score must be in range 0 to 100");
        }

        List<String> listOfBages = usr.getBadges();

        if(listOfBages == null){
            listOfBages = new ArrayList<>();
        }

        if(score >=1 && score<30){
            if(!listOfBages.contains(BadgeConstants.CODE_NINJA)){
                listOfBages.add(BadgeConstants.CODE_NINJA);
            }
        }
        else if(score >=30 && score<60){
            if(!listOfBages.contains(BadgeConstants.CODE_CHAMP)){
                listOfBages.add(BadgeConstants.CODE_CHAMP);
            }
        }
        else{
            if(!listOfBages.contains(BadgeConstants.CODE_MASTER)){
                listOfBages.add(BadgeConstants.CODE_MASTER);
            }
        }

        usr.setScore(score);
        usr.setBadges(listOfBages);

        return userRepository.save(usr);
    }

    @Override
    public String deleteUserById(String userId) throws UserNotFoundException {


        User usr = null;
        try{
            usr = userRepository.findById(userId).get();
            userRepository.deleteById(userId);
        }
        catch (Exception e){
            throw new UserNotFoundException("User not found");
        }

        return "userId:"+ userId +" Successfully deleted";
    }
}
