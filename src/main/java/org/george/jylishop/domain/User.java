package org.george.jylishop.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulya on 23.10.2016.
 */
@Data
@Entity
@Table(name = "users")
public class User {

    @Column(name = "username")
    @Id
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserRole> roles = new ArrayList<>();

    private double money;

    private String avatar;


}