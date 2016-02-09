package com.rpicloud.repositories;

import com.rpicloud.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by mixmox on 08/02/16.
 */

@RepositoryRestResource(collectionResourceRel = "user", path = "users")
public interface UserRepository extends MongoRepository<User, String> {
    User findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
}
