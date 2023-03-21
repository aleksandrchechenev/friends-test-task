package com.example.friendstesttask.controller;

import com.example.friendstesttask.dto.*;
import com.example.friendstesttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody AddUserRequestDto userDto) {
        UserResponseDto userResponseDto = userService.addUser(userDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.getUserById(id);
        if (userResponseDto.getId() != null){
            return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("User with id = " + id + " not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> setUser(@RequestBody SetUserRequestDto userDto) {
        boolean setUser = userService.setUser(userDto);
        if (setUser) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("User with id = " + userDto.getId() + " not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/friends")
    public ResponseEntity<?> addFriend(@RequestBody AddFriendRequestDto addFriendRequestDto) {
        boolean addFriend = userService.addFriend(addFriendRequestDto.getUserId(), addFriendRequestDto.getFriendId());
        if (addFriend) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping (path = "/friends")
    public ResponseEntity<?> removeFriend(@RequestBody RemoveFriendRequestDto removeFriendRequestDto) {
        boolean removeFriend = userService.removeFriend(removeFriendRequestDto.getUserId(), removeFriendRequestDto.getFriendId());
        if (removeFriend) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("One or both users are not found", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/friends/{userId}")
    public ResponseEntity<?> getUsersFriends(@PathVariable Long userId) {
        GetFriendsResponseDto usersFriends = userService.getUsersFriends(userId);
        if (usersFriends.isFound()) {
            return new ResponseEntity<>(usersFriends.getFriendsList(), HttpStatus.OK);
        }
        return new ResponseEntity<>("User with id = " + userId + " not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/friends/max-number")
    public ResponseEntity<?> getUserWithMaxNumberOfFriends() {
        return new ResponseEntity<>(userService.getUserWithMaxNumberOfFriends() ,HttpStatus.OK);
    }

}
