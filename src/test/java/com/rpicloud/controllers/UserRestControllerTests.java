package com.rpicloud.controllers;

import com.rpicloud.UserServiceApplication;
import com.rpicloud.models.User;
import com.rpicloud.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by kaspernissen on 09/02/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserServiceApplication.class)
@WebAppConfiguration
public class UserRestControllerTests extends BaseControllerTests {

    protected List<User> userList;

    @Autowired
    protected UserRepository userRepository;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.userRepository.deleteAll();

        this.userList = new ArrayList<User>();
        this.userList.add(new User("Peter", "Parker", "New York", LocalDate.of(1975, Month.AUGUST, 20)));
        this.userList.add(new User("Bruce", "Wayne", "Secret", LocalDate.of(1986, Month.JUNE, 1)));

        userRepository.save(this.userList.get(0));
        userRepository.save(this.userList.get(1));
        userRepository.findAll().forEach(System.out::println);
    }


    @Test
    public void readAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is(userList.get(0).getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(userList.get(0).getLastName())));

    }

}
