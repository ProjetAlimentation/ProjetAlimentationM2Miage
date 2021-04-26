package com.miage.backendspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "USERS")
@NamedQueries({
        @NamedQuery(name="User.login", query="select count(u) from USERS u where u.username=:username and u.password = :password")
})
@AllArgsConstructor
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;
    private String password;


    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private ProductCart productCart;
}
