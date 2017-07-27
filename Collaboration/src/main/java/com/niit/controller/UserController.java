package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.FriendDao;
import com.niit.dao.UserDao;
import com.niit.domain.User;

@RestController
public class UserController {

	@Autowired
	UserDao userDao;

	@Autowired
	FriendDao friendDao;
	@Autowired
	User user;
	@Autowired
	HttpSession session;
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public ResponseEntity<List<User>> list() {

		List<User> listusers = userDao.list();
		if (listusers.isEmpty()) {
			user.setErrorCode("404");
			user.setErrorMessage("No users are available");
			listusers.add(user);
		}

		return new ResponseEntity<List<User>>(listusers, HttpStatus.OK);

	}

	// insert the user
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User user) {
		logger.debug("->->->->calling method addUser");
		if (userDao.getUserById(user.getUserId()) == null) {
			logger.debug("->->->->User is going to create with id:" + user.getUserId());
			user.setIsOnline("N");
			user.setStatus("N");
			if (userDao.save(user) == true) {
				user.setErrorCode("200");
				user.setErrorMessage(
						"Thank you  for registration. You have successfully registered as " + user.getRole());
			} else {
				user.setErrorCode("404");
				user.setErrorMessage("Could not complete the operatin please contact Admin");

			}

			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

		else {

			logger.debug("->->->->User already exist with id " + user.getUserId());
			user.setErrorCode("404");
			user.setErrorMessage("User already exist with id : " + user.getUserId());
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

	}

	// delete the user
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {

		userDao.delete(userId);

		return new ResponseEntity<String>("User Deleted Successfully", HttpStatus.OK);
	}

	// update the user

	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		logger.debug("Calling method updateUser");
		if (userDao.getUserById(user.getUserId()) == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist with" + user.getUserId());
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			userDao.update(user);
			user.setErrorCode("200");
			user.setErrorMessage("Successfully updated with" + user.getUserId());
			logger.debug("User updated successfully");

			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

	}

	// getUser
	@RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
		logger.debug("calling method getUser");
		User user = userDao.getUserById(userId);
		if (user == null) {
			logger.debug("User does not exist wiht userId" + userId);
			user = new User(); // To avoid NLP - NullPointerException
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			logger.debug("**User exist with id" + userId);
			logger.debug(user.getFirstname());
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	// accept user
	@RequestMapping(value = "/approve/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> accept(@PathVariable("userId") String userId) {
		logger.debug("Starting of the method accept");
		User user = userDao.getUserById(userId);

		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");
		if (loggedInUserRole.equals("ROLE_ADMIN")) {
			user = updateStatus(userId, "A", "");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			user.setErrorCode("404");
			user.setErrorMessage("you have to be authorized as " + loggedInUserRole);
		}
		logger.debug("Ending of the method accept");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	// reject user
	@RequestMapping(value = "/reject/{userId}/{remarks}", method = RequestMethod.GET)
	public ResponseEntity<User> reject(@PathVariable("userId") String userId, @PathVariable("remarks") String remarks) {
		logger.debug("Starting of the method reject");

		user = updateStatus(userId, "R", remarks);
		logger.debug("Ending of the method reject");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	// updatestatus method in accept and reject methods..
	private User updateStatus(String userId, String status, String remarks) {
		logger.debug("Starting of the method updateStatus");

		logger.debug("status: " + status);
		user = userDao.getUserById(userId);

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Could not update the status to " + status);
		} else {

			user.setStatus(status);
			user.setRemarks(remarks);

			userDao.update(user);

			user.setErrorCode("200");
			user.setErrorMessage("Updated the status successfully");
		}
		logger.debug("Ending of the method updateStatus");
		return user;

	}

	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public ResponseEntity<User> myProfile() {
		logger.debug("calling method myProfile");
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		User user = userDao.getUserById(loggedInUserId);
		if (user == null) {
			logger.debug(" ----User does not exist wiht id" + loggedInUserId);
			user = new User(); // It does not mean that we are inserting new row
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		} else {

			logger.debug("---User exist with id" + loggedInUserId);
			logger.debug(user.getFirstname());
			user.setErrorCode("200");
			user.setErrorMessage("Successfully displaying the user details");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	/*@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	public ResponseEntity<User> validateUser(@RequestBody User user) {
		User curr_user = userDao.validate(user.getUserId(), user.getPassword());
		if (curr_user == null) {
			curr_user.setErrorCode("404");
			curr_user.setErrorMessage("you are not registered");

		} else {
			session.setAttribute("loggedInUserId", curr_user.getUserId());
			session.setAttribute("loggedInUserRole", curr_user.getRole());
			session.setAttribute("loggedInUser", curr_user);
			curr_user.setIsOnline("true");
			userDao.update(curr_user);
			curr_user.setErrorCode("200");
			curr_user.setErrorMessage("you are logged in as" + user.getFirstname());
		}
		return new ResponseEntity<User>(curr_user, HttpStatus.OK);
	}*/

	// login
	/*
	 * { "id": "alm1", "password": "alm1" }
	 */
	// @RequestMapping(value = "/login", method = RequestMethod.POST)
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		logger.debug("->->->->calling method authenticate");
		user = userDao.validate(user.getUserId(), user.getPassword());
		if (user == null) {
			user = new User(); // Do wee need to create new user?
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Credentials.  Please enter valid credentials");
			logger.debug("->->->->In Valid Credentials");

		} else

		{
			user.setErrorCode("200");
			user.setErrorMessage("You have successfully logged in.");
			user.setIsOnline("Y");
			logger.debug("->->->->Valid Credentials");
			/* session.setAttribute("loggedInUser", user); */
			session.setAttribute("loggedInUserId", user.getUserId());
			session.setAttribute("loggedInUserRole", user.getRole());

			logger.debug("You are loggin with the role : " + session.getAttribute("loggedInUserRole"));

			friendDao.setOnline(user.getUserId());
			userDao.setOnline(user.getUserId());
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<User> logout(HttpSession session) {
		logger.debug("----calling method logout");
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");

		userDao.update(user);

		friendDao.setOffline(loggedInUserId);
		userDao.setOffLine(loggedInUserId);

		session.invalidate();
		user = userDao.getUserById(loggedInUserId);
		user.setErrorCode("200");
		user.setErrorMessage("You have successfully loggedout");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	};

}
