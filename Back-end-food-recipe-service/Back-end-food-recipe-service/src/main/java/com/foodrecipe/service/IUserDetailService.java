package com.foodrecipe.service;

import com.foodrecipe.entity.Role;
import com.foodrecipe.entity.User;

public interface IUserDetailService {

	User getUserByName(String userName);

	boolean addUser(User user);

	void updateUser(User user);
	
	Role getRoleById(int id);
}
