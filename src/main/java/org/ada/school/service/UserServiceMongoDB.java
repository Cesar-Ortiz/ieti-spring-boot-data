package org.ada.school.service;

import org.ada.school.dto.UserDto;
import org.ada.school.model.User;
import org.ada.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceMongoDB implements UserService{

    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository )
    {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> all() {
        List<User> users = new ArrayList<User>();
        users=userRepository.findAll();
        return users;
    }

    @Override
    public boolean deleteById(String id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User update(UserDto userDto, String id) {
        if(userRepository.existsById(id)){
            User user = userRepository.findById(id).get();
            user.update(userDto);
            userRepository.save(user);
            return user;
        }
        return null;
    }
}
