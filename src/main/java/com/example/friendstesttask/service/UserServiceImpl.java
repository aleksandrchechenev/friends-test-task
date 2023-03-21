package com.example.friendstesttask.service;

import com.example.friendstesttask.Entity.User;
import com.example.friendstesttask.dto.AddUserRequestDto;
import com.example.friendstesttask.dto.GetFriendsResponseDto;
import com.example.friendstesttask.dto.UserResponseDto;
import com.example.friendstesttask.dto.SetUserRequestDto;
import com.example.friendstesttask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public UserResponseDto addUser(AddUserRequestDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());

        repository.save(user);

        return new UserResponseDto().fromUser(user);
    }
    @Override
    public UserResponseDto getUserById(Long userId) {
        Optional<User> optional = repository.findById(userId);
        UserResponseDto userResponseDto = new UserResponseDto();

        if (optional.isPresent()) {
            User user = optional.get();
            return userResponseDto.fromUser(user);
        }
        return userResponseDto;
    }
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = repository.findAll();

        return users.stream()
                .map(user -> new UserResponseDto().fromUser(user))
                .collect(Collectors.toList());
    }
    @Override
    public void deleteUser(Long userId) {
        repository.deleteById(userId);
    }
    @Override
    public boolean setUser(SetUserRequestDto userDto) {
        Optional<User> optional = repository.findById(userDto.getId());
        if (optional.isPresent()) {
            User user = optional.get();
            user.setName(userDto.getName());
            user.setAge(userDto.getAge());
            repository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean addFriend(Long userId, Long friendId) {
        Optional<User> optionalUser = repository.findById(userId);
        Optional<User> optionalFriend = repository.findById(friendId);

        if (optionalUser.isPresent() && optionalFriend.isPresent()) {
            User user = optionalUser.get();
            User friend = optionalFriend.get();

            Set<User> userFriends = user.getFriends();
            userFriends.add(friend);
            user.setFriends(userFriends);

            Set<User> userFriendOf = user.getFriendOf();
            userFriendOf.add(friend);
            user.setFriendOf(userFriendOf);

            repository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeFriend(Long userId, Long friendId) {
        Optional<User> optionalUser = repository.findById(userId);
        Optional<User> optionalFriend = repository.findById(friendId);

        if (optionalUser.isPresent() && optionalFriend.isPresent()) {
            User user = optionalUser.get();
            User friend = optionalFriend.get();

            Set<User> userFriends = user.getFriends();
            userFriends.remove(friend);
            user.setFriends(userFriends);

            Set<User> userFriendOf = user.getFriendOf();
            userFriendOf.remove(friend);
            user.setFriendOf(userFriendOf);

            repository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public GetFriendsResponseDto getUsersFriends(Long userId) {
        Optional<User> optionalUser = repository.findById(userId);
        GetFriendsResponseDto friendsResponseDto = new GetFriendsResponseDto();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            List<UserResponseDto> usersFriends = user.getFriends().stream()
                    .map(friend -> new UserResponseDto().fromUser(friend))
                    .collect(Collectors.toList());

            friendsResponseDto.setFound(true);
            friendsResponseDto.setFriendsList(usersFriends);

            return friendsResponseDto;
        }
        friendsResponseDto.setFound(false);
        return friendsResponseDto;
    }

    @Override
    public UserResponseDto getUserWithMaxNumberOfFriends() {
        User user = repository.getUserWithMaxNumberOfFriends();
        return new UserResponseDto().fromUser(user);
    }
}
