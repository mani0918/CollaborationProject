package com.niit.dao;

import java.util.List;

import com.niit.domain.UserTable;

public interface UserTableDao {

	public boolean insertUserTable(UserTable userTable);

	public List<UserTable> list();

	public boolean deleteUserTable(int id);

	public UserTable getUserTableById(int id);

	public boolean validate(String emailId , String password);
}
