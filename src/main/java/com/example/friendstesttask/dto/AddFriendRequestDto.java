package com.example.friendstesttask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddFriendRequestDto {
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("friend_id")
    private Long friendId;
}
