package com.travelblog.repository;

import com.travelblog.model.Tag;
import com.travelblog.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findOneByEmail(String email);

}
