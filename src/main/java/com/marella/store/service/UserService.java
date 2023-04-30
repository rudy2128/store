package com.marella.store.service;

import com.marella.store.model.User;
import com.marella.store.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }
    public User getById(Long id){
        return userRepository.getReferenceById(id);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }



}
