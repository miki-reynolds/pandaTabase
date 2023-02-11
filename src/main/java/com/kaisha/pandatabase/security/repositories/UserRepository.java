package com.kaisha.pandatabase.security.repositories;

import com.kaisha.pandatabase.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsernameEquals(String username);
}



/*
By marking the repository as exported = false, the developer is indicating that this repository
should not be part of the public API and should only be used by the application internally.
 */