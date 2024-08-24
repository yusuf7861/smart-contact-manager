package com.scm.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
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

    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

//    user can log in using many social accounts
    @Enumerated(value = EnumType.STRING)
    private Providers providers = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade =  CascadeType.ALL, fetch  = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contact = new ArrayList<>();



}
