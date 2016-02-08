package com.rpicloud.controllers;

import com.rpicloud.models.User;
import com.rpicloud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by mixmox on 08/02/16.
 */
@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getAllUsers(){
         return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public User getUserById(@PathVariable String id){
        return repository.findOne(id);
    }
}
