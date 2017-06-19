package com.niit.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserTableDao;
import com.niit.domain.UserTable;

@RestController
public class UserTableController {

	@Autowired
	UserTableDao userTableDao;

	@Autowired
	UserTable userTable;

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public ResponseEntity<List<UserTable>> list() {

		List<UserTable> listuserTables = userTableDao.list();

		return new ResponseEntity<List<UserTable>>(listuserTables, HttpStatus.OK);

	}

	// insert the userTable
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public ResponseEntity<String> addUserTable(@RequestBody UserTable userTable) {
		
		userTable.setRole("ROLE_USER");
		userTable.setStatus("NA");
		userTable.setIsOnline("False");
		
		userTableDao.insertUserTable(userTable);
		return new ResponseEntity<String>("Successfully inserted", HttpStatus.OK);

	}

	// delete the userTable
	@RequestMapping(value = "/deleteUserTable/{userTableId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUserTable(@PathVariable("userTableId") int userTableId) {

		userTableDao.deleteUserTable(userTableId);

		return new ResponseEntity<String>("UserTable Deleted Successfully", HttpStatus.OK);
	}

	
	
	// update the userTable
	
	/*@RequestMapping(value = "/updateUserTable/{userTableId}", method = RequestMethod.PUT)
	public ResponseEntity<UserTable> updateUserTable(@PathVariable("userTableId") int userTableId, @RequestBody UserTable userTable) {
		UserTable curr_userTable = userTableDao.getUserTableById(userTableId);
		curr_userTable.setUserTableContent(userTable.getUserTableContent());
		userTableDao.insertUserTable(curr_userTable);
		return new ResponseEntity<UserTable>(curr_userTable, HttpStatus.OK);

	}*/
	
}
