package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.UserRequestModel;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

	public String createUser(UserRequestModel userdetails){
		//userdetails.setUserId(UUID.randomUUID().toString());
		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		User userentity = new User();
		userentity.setUsername(userdetails.getUserName());
		userentity.setEmail(userdetails.getEmail());
		userentity.setPassword(bCryptPasswordEncoder.encode(userdetails.getPassword()));

		repository.save(userentity);

		return "User Added";
	}



	public List<User> fetchallusers() {
		List<User> target = new ArrayList<>();
		target = repository.findAll();
		return target;
	}

}
