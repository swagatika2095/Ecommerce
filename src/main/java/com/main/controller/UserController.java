package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.User;
import com.main.service.UserService;



@RestController
public class UserController {
	
	@Autowired
	UserService userdao;

	@RequestMapping(value = "/getDetails", method = RequestMethod.GET)
	public ResponseEntity getDetails() {
		try {
			List<User> user = userdao.getAllUserDetails();
			if (user.size() == 0)
				return new ResponseEntity<String>("No Records Found", HttpStatus.BAD_REQUEST);
			else
				return new ResponseEntity<List<User>>(user, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getDetailsById/{Id}", method = RequestMethod.GET)
	public ResponseEntity getDetailsById(@PathVariable("Id") int id) {
		try {
			User user = userdao.getUserDetailsById(id);
			if (user == null) {
				return new ResponseEntity<String>("No Records Found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/updateDetails", method = RequestMethod.PUT)
	public ResponseEntity updateDetails(@RequestBody User user) {
		try {
			userdao.saveOrUpdate(user, user.getUser_Id());
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/deleteDetails/{Id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteDetails(@PathVariable("Id") int id) {
		try {
			userdao.delete(id);
			return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/createUserDetails", method = RequestMethod.POST)
	public ResponseEntity createUserDetails(@RequestBody User user) {
		try {
			userdao.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
