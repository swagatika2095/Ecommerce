package com.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.User;
@Repository
public interface UserDao extends JpaRepository<User, Integer>{

}
