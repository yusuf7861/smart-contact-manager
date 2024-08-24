package com.scm.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Contact {
    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phoneNumber;
    private String address;
    private String picture;
    @Column(length = 1000)
    private String description;
    private boolean favourite = false;
    private String category;
    private String instagramLink;
    private String websiteLink;
    private String linkedinLink;


    // Ye @ManyToOne annotation Contact aur User entities ke beech Many-to-One relationship ko define karta hai.
    // Iska matlab hai ki bahut saari Contact entities ek hi User ke sath associated ho sakti hain.
    // 'user' field wo User entity ko refer karta hai jo is Contact ka malik hai (owner).
    // Yeh One-to-Many relationship ka inverse side hai jo User entity mein define kiya gaya hai.
    // Database mein usually ek foreign key column hoga Contact table mein jo User table se link karega.
    // Yaha par @JoinColumn explicitly use nahi kiya gaya hai, toh default column ka naam user_id hoga Contact table mein.
    @ManyToOne
    private User user;  // Is Contact ko own karne wala User entity ko represent karta hai



    // Ye @OneToMany annotation Contact aur SocialLink entities ke beech One-to-Many relationship ko define karta hai.
    // Iska matlab hai ki ek Contact ke paas multiple SocialLink entities ho sakti hain.
    // 'mappedBy = "contact"' ka matlab hai ki SocialLink entity mein 'contact' field relationship ko own karta hai.
    // CascadeType.ALL ka matlab hai ki agar Contact entity ke sath koi operation hota hai
    // (jaise save, update, delete), toh woh operation SocialLink entities pe bhi apply hoga.
    // FetchType.EAGER ka matlab hai ki jab Contact entity fetch kiya jayega, tab SocialLink entities bhi turant fetch hongi.
    // orphanRemoval = true ka matlab hai agar koi SocialLink entity ko is Contact se unlink kiya jata hai
    // (jaise list se remove karne par), toh woh entity database se automatically delete ho jayegi.
    // 'links' list ko empty ArrayList se initialize kiya gaya hai taaki NullPointerException avoid kiya ja sake.
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();


}
