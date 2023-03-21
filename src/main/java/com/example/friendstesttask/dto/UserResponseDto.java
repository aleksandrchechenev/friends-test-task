package com.example.friendstesttask.dto;

import com.example.friendstesttask.Entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("age")
    private int age;

    public UserResponseDto fromUser(User user) {
        this.setId(user.getId());
        this.setName(user.getName());
        this.setAge(user.getAge());
        return this;
    }
}
