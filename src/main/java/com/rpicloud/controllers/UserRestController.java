package com.rpicloud.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mixmox on 08/02/16.
 */
@RestController
public class UserRestController {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<String> getAllUsers() {
        List<String> users = new ArrayList<String>();
        users.add("Kasper");
        users.add("Martin");
        users.add("Christian");
        return users;
    }
}
