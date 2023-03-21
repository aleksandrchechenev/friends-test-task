package com.example.friendstesttask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequestDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("age")
    private Integer age;

}
