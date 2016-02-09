package com.rpicloud.controllers;

import com.google.common.base.Throwables;
import com.mongodb.MongoClientException;
import com.mongodb.MongoSocketException;
import com.mongodb.MongoTimeoutException;
import com.rpicloud.exceptions.MongoConnectionException;
import com.rpicloud.models.User;
import com.rpicloud.repositories.UserRepository;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

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
    public ResponseEntity<Collection<User>> getAllUsers(){

         return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) throws MongoConnectionException {
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
