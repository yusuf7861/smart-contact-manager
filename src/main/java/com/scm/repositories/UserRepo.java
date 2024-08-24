package com.scm.repositories;

import com.scm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
//    Optional<User> findByEmailandPassword(String email, String password);
}
        // This is an interface that defines a repository for the User entity.
        // It extends JpaRepository, which provides built-in methods for database operations
        // JpaRepository is a Spring Data interface that provides CRUD operations,
        // pagination, and sorting functionality for the User entity.
        // The first type parameter <User> specifies the entity type that this repository manages.
        // The second type parameter <String> specifies the type of the entity's primary key (in this case, String).
