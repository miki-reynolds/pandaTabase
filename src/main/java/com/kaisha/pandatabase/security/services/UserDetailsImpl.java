package com.kaisha.pandatabase.security.services;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.kaisha.pandatabase.security.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Service;


public class UserDetailsImpl implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        UserDetailsImpl user = (UserDetailsImpl) obj;
        return Objects.equals(id, user.id);
    }
}

/*
This is a Java class that implements the UserDetails interface from the Spring Security framework.
The UserDetails interface defines a contract for storing user-related information that is required for authentication
and authorization purposes.
The UserDetailsImpl class serves as a wrapper for the User model, which represents a user in your application.
This class is responsible for providing the necessary information about a user to the Spring Security framework,
such as the username, password, and roles of the user.

The UserDetailsImpl class is also serializable, which means that it can be stored in a session or other persistence layer.
The class implements several methods from the UserDetails interface, including:

getUsername(): Returns the username of the user.

getPassword(): Returns the password of the user.

getAuthorities(): Returns the roles or authorities of the user as a collection of GrantedAuthority objects.

isAccountNonExpired(): Returns a boolean indicating whether the user's account has expired.

isAccountNonLocked(): Returns a boolean indicating whether the user's account is locked.

isCredentialsNonExpired(): Returns a boolean indicating whether the user's credentials (e.g. password) have expired.

isEnabled(): Returns a boolean indicating whether the user is enabled or disabled.

The class also includes additional methods for getting the user's ID and email address.

The build() method is a static factory method that creates a new UserDetailsImpl instance from a User model.
This method maps the user's roles to GrantedAuthority objects and passes them to the constructor of the UserDetailsImpl class.


GrantedAuthority is an interface in the Spring Security framework that represents the authorization granted to a user.
In other words, it represents the roles and permissions that a user has within a system.
The GrantedAuthority interface has one method, getAuthority(), which returns the name of the authority,
typically a role such as "ROLE_ADMIN" or "ROLE_USER".
In the example you provided, the UserDetailsImpl class implements the UserDetails interface,
which is a core interface in Spring Security that represents the user data required by the framework
to perform authentication and authorization. In this implementation,
the UserDetailsImpl class maps the roles of a User entity to GrantedAuthority objects and provides them to
the Spring Security framework to determine if the user has sufficient permissions to access a given resource.


 @Serial is a marker annotation indicating that the class is serializable, but it's not used in Java.
 serialVersionUID is a field with a private static final long type and is used to maintain serialization compatibility
 of a class between different versions. The serialVersionUID field is a unique identifier for the class,
 and it is used by the serialization runtime to ensure that the same class that was serialized can be deserialized properly, even if the class definition has changed. If the class definition changes in an incompatible way, a new serialVersionUID must be generated, or the deserialization process will fail.


 */