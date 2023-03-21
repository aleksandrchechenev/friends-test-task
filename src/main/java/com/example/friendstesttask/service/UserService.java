package com.example.friendstesttask.service;

import com.example.friendstesttask.dto.AddUserRequestDto;
import com.example.friendstesttask.dto.GetFriendsResponseDto;
import com.example.friendstesttask.dto.UserResponseDto;
import com.example.friendstesttask.dto.SetUserRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponseDto addUser(AddUserRequestDto userDto);
    UserResponseDto getUserById(Long userId);
    List<UserResponseDto> getAllUsers();
    void deleteUser(Long userId);
    boolean setUser(SetUserRequestDto userDto);
    boolean addFriend(Long userId, Long friendId);
    boolean removeFriend(Long userId, Long friendId);
    GetFriendsResponseDto getUsersFriends(Long userId);
    UserResponseDto getUserWithMaxNumberOfFriends();
}
