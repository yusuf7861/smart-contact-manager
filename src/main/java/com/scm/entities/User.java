package com.scm.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    private String userId;
    @Column(name = "user_name", nullable = false)
    private String name;
    private String email;
    @Column(nullable = false)
    private String password;
    private String profileLink;
    @Column(length = 1000)
    private String about;
    @Column(nullable = false)
    private String phoneNumber;


    private boolean enabled = true;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;


//    user can log in using many social accountsRF
    @Enumerated(value = EnumType.STRING)
    private Providers providers = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade =  CascadeType.ALL, fetch  = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contact = new ArrayList<>();



    // UserDetails interface methods, getPassword() and getUsername() are used to authenticate the user

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // List of roles(USER, ADMIN, etc)
        // collection of SimpleGrantedAuthority[roles{user, admin}]
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
        // in this project email is username
        return this.email;
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
    public String getPassword() {
        return this.password;
    }
    // we have getPassword() but it is already created using lombok getter
}
