package com.rpicloud.repositories;

import com.rpicloud.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by mixmox on 08/02/16.
 */

public interface UserRepository extends MongoRepository<User, String> {
    User findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
}
