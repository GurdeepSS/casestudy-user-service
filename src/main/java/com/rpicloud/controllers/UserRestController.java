package com.rpicloud.controllers;

import com.rpicloud.exceptions.MongoConnectionException;
import com.rpicloud.exceptions.ResourceNotFoundException;
import com.rpicloud.models.User;
import com.rpicloud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by mixmox on 08/02/16.
 */
@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<User>> getAllUsers() throws MongoConnectionException {
        ResponseEntity<Collection<User>> users;
        try {
            users = new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        }
        catch (DataAccessResourceFailureException exception) {
            throw new MongoConnectionException(exception.getMessage(), exception);
        }

        return users;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) throws MongoConnectionException, ResourceNotFoundException {
        User user;
        try{
            user = repository.findOne(id);
        }
        catch (DataAccessResourceFailureException exception){
            throw new MongoConnectionException(exception.getMessage(), exception);
        }

        if(user == null){
            throw new ResourceNotFoundException("No user with that id");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
