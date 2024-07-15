package com.app.chatapp.dao;


import com.app.chatapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserId(int userId);
    Optional<UserEntity> findByUsername(String username);
}
