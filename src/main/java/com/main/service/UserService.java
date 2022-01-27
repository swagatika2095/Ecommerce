package com.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dao.UserDao;
import com.main.entity.User;



@Service
public class UserService{



	@Autowired
	UserDao userdao;
	
	public List<User> getAllUserDetails(){
		List<User> user = new ArrayList<User>();  
		userdao.findAll().forEach(user1 -> user.add(user1)); 
		return user;  
	}
	public  User getUserDetailsById(int id)   
	{  
	return userdao.findById(id).get();  
	} 
	public  void saveOrUpdate(User user,int userId)   
	{  
		userdao.save(user);  
	}
	public  void save(User user)   
	{  
		userdao.save(user);  
	} 
	public  void delete(int id)   
	{  
		userdao.deleteById(id);  
	}
	public Optional<User> findById(int userId) {
		// TODO Auto-generated method stub
		return userdao.findById(userId);
	}




}
