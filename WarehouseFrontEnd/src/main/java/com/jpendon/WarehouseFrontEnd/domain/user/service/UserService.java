package com.jpendon.WarehouseFrontEnd.domain.user.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.jpendon.WarehouseFrontEnd.domain.user.model.User;

import reactor.core.publisher.Mono;

@Service
public class UserService implements IUserService {
	
	private final WebClient webClient; 
	
	public UserService(WebClient.Builder builder) {
		webClient = builder.baseUrl("http://localhost:8080/api").build();
	}
	
	@Override
	public User[] getUsers() {
		return webClient.get()
				.uri("/users/")
				.retrieve()
				.bodyToMono(User[].class).block();
	}
	
	@Override
	public User getUserById(Long id) {		
		return webClient.get()
				.uri("/users/" + id)
				.retrieve()
				.bodyToMono(User.class).block();
	}

	@Override
	public void createUser(User user) {
		webClient.post()
			.uri("/users/")
			.bodyValue(user)
			.retrieve()
			.bodyToMono(User.class).block();
	}

	@Override
	public void updateUser(User user) {		
		webClient.put()
			.uri("/users/")
			.bodyValue(user)
			.retrieve()
			.bodyToMono(User.class);
	}

	@Override
	public void deleteUser(Long id) {
		webClient.delete()
			.uri("/users/" + id)
			.retrieve()
			.bodyToMono(String.class).block();
	}
	
	


	
}
