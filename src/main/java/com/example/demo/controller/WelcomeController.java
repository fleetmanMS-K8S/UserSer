package com.example.demo.controller;

import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.model.UserRequestModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class  WelcomeController {

	@Autowired
	private Environment env;

	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository repository;

	@Autowired
	CustomUserDetailsService service;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome to Java " + env.getProperty("checking") + env.getProperty("token.expirationtime") + env.getProperty("secret");
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {

		log.info("Authentication attempted");
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
			);
		} catch (Exception ex) {
			throw new Exception("invalid username/password");
		}
		String token = jwtUtil.generateToken(authRequest.getUserName());
				return token + " 36000000";
	}


	@GetMapping("/authenticate")
	public ResponseEntity fetchusers() throws Exception {

		log.info("Fetching all users");

		List<User> target = new ArrayList<>();
		target = service.fetchallusers();

		for (User eachuser: target) {
			System.out.println(eachuser.getUsername());
		}
		//return ResponseEntity.status(HttpStatus.CREATED).body();

		return ResponseEntity.status(HttpStatus.OK).body(target);


	}


	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE })
	public String createUser(@Valid @RequestBody UserRequestModel userdetails){

		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

	//	UserDto userdtodetails = modelmapper.map(userdetails, UserDto.class);
		log.info("Creating a new user");

		return service.createUser(userdetails);


	}




}
