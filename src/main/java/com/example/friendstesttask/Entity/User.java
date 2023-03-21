package com.example.friendstesttask.Entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;

    @ManyToMany
    @JoinTable(name = "users_friends",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "friends_id"))
    private Set<User> friends;

    @ManyToMany
    @JoinTable(name="users_friends",
            joinColumns=@JoinColumn(name="friends_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    private Set<User> friendOf;

}
