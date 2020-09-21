package com.example.demo;

import com.example.demo.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
public class JwtmsApplication {
	
	@Autowired
	private UserRepository repository;
	
	/*
	@PostConstruct
	public void initUsers() {
		
		List<User> users = Stream.of(
				
				new User(101,"manish","password","manishrchandran@gmail.com"),
				new User(102,"mukesh","password1","mukeshrchandran@gmail.com")
				).collect(Collectors.toList());
		
		repository.saveAll(users);
	}   */


	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/*").allowedHeaders("*").allowedOrigins("*").allowedMethods("*").allowCredentials(true);
			}


		};
	}

	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}



	public static void main(String[] args) {
		SpringApplication.run(JwtmsApplication.class, args);
	}

}
