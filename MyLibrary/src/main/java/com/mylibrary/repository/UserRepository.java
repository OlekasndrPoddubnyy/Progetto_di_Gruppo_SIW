package com.mylibrary.repository;

import org.springframework.data.repository.CrudRepository;

import com.mylibrary.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}