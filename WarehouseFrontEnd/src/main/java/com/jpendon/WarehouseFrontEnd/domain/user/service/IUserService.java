package com.jpendon.WarehouseFrontEnd.domain.user.service;

import com.jpendon.WarehouseFrontEnd.domain.user.model.User;

public interface IUserService {

	public User[] getUsers();
	
	public User getUserById(Long id);
	
	public void createUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(Long id);
	
}
