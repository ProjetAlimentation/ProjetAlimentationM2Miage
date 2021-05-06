package com.miage.backendspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "USERS")
@NamedQueries({
        @NamedQuery(name="User.login", query="select count(u) from USERS u where u.username=:username and u.password = :password")
})
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;
    private String password;


    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private ProductCart productCart;


    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private Profile profile;


    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Monitoring> monitoring = new HashSet<>();

}
