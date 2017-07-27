package com.niit.dao;

import java.util.List;

import com.niit.domain.User;

public interface UserDao {

	public boolean save(User user);

	public boolean update(User user);

	public boolean delete(String userId);

	public User getUserById(String userId);

	public List<User> list();

	// public User getUserByEmail(String email);

	public User validate(String userId, String password);

	public void setOnline(String userId);

	public void setOffLine(String userId);

}
