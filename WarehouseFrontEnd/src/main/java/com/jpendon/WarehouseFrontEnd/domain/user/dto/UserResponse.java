package com.jpendon.WarehouseFrontEnd.domain.user.dto;

public record UserResponse (
		Long id, 
		String firstName, 
		String lastName, 
		String address, 
		String email
		){

}
