package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface  UserRepository  extends CrudRepository<User,Integer> {

	 User findByUsername(String username);
	 List<User> findAll();
}
