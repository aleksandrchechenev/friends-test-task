package com.example.friendstesttask.repository;

import com.example.friendstesttask.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        @Query(value = "SELECT id, name, age FROM users INNER JOIN users_friends ON users.id=users_friends.user_id GROUP BY users.id ORDER BY count(friends_id) DESC LIMIT 1", nativeQuery = true)
        User getUserWithMaxNumberOfFriends();

}
