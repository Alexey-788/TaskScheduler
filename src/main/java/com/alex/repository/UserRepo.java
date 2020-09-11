package com.alex.repository;

import com.alex.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);
}
