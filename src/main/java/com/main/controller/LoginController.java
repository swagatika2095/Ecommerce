package com.main.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Order_Table;
import com.main.entity.User;
import com.main.service.OrderService;
import com.main.service.UserService;


@RestController
public class LoginController {
	@Autowired
	UserService userservice;
	@Autowired
	OrderService orderservice;
	

	
	   @RequestMapping(value = "/SignUp", method = RequestMethod.POST)
	   public ResponseEntity createUserDetails(@RequestBody User user) {
		   
		   String pass= user.getPassword();
		   BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		   String encodedPassword = passwordEncoder.encode(pass);
		   user.setPassword(encodedPassword);
		   Optional<User> user1= userservice.findById(user.getUser_Id());
		   try {
		   if(!user1.isEmpty()) {
			   userservice.saveOrUpdate(user,user.getUser_Id());
			   return new ResponseEntity<String>("User Updated", HttpStatus.OK);
		   }
		   else {
			   userservice.save(user);
			   return new ResponseEntity<String>("User Created", HttpStatus.CREATED);
		   }
		   }catch(Exception ex) {
			   return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		   }
	   }
	   @RequestMapping(value = "/Login", method = RequestMethod.POST)
	   public ResponseEntity updateDetails(@RequestBody String userDetails) {
		   try {
		   BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		   JSONObject jsonRequestData = new JSONObject(userDetails);
		   String userId = jsonRequestData.getString("User_Id");
		   String pass=jsonRequestData.getString("Password");
		   int userid=Integer.parseInt(userId);
		   Optional<User> user1= userservice.findById(userid);
		   List<Order_Table> order= new ArrayList<Order_Table>();
		   if(!user1.isEmpty() && passwordEncoder.matches(pass, user1.get().getPassword())) {
			   Collection<Order_Table> orderres= orderservice.orderDetailsBasedOnUser(userid);
			   order=orderres.stream().collect(Collectors.toList());
			   return new ResponseEntity<List<Order_Table>>(order, HttpStatus.OK);
		   }else {
			   return new ResponseEntity<String>("Invalid Login", HttpStatus.BAD_REQUEST);
		   }
		   }catch(Exception ex) {
			   return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		   }
	   }
}
