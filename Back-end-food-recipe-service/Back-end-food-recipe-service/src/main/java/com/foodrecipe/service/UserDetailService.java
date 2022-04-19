package com.foodrecipe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrecipe.dao.IUserDao;
import com.foodrecipe.entity.Role;
import com.foodrecipe.entity.User;

@Service
public class UserDetailService implements IUserDetailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailService.class);

	@Autowired
	private IUserDao userDao;

	@Override
	public User getUserByName(String userName) {
		LOGGER.info("getUserByName() invoked");
		return userDao.getUserByName(userName);
	}

	@Override
	public boolean addUser(User user) {
		LOGGER.info("addUser() invoked");
		return userDao.addUser(user);
	}

	@Override
	public void updateUser(User user) {
		LOGGER.info("updateUser() invoked");
		userDao.updateUser(user);

	}

	@Override
	public Role getRoleById(int id) {
		LOGGER.info("updateUser() invoked");
		return userDao.getRoleById(id);
	}

}
