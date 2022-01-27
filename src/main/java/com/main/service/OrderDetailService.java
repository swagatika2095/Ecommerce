package com.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dao.OrderDetailDao;
import com.main.entity.Order_Details;

@Service
public class OrderDetailService {
	@Autowired
	OrderDetailDao orderdetailsdao;
	
	public List<Order_Details> getAllOrderDetails(){
		List<Order_Details> orderdetails = new ArrayList<Order_Details>();  
		orderdetailsdao.findAll().forEach(orderdetails1 -> orderdetails.add(orderdetails1)); 
		return orderdetails;  
	}
	public  Order_Details getOrderDetailsById(int id)   
	{  
	return orderdetailsdao.findById(id).get();  
	} 
	public  void saveOrUpdate(Order_Details order,int orderdetailsId)   
	{  
		orderdetailsdao.save(order);  
	}
	@Transactional
	public  void save(Order_Details order)   
	{  
		orderdetailsdao.save(order);
		
	} 
	public  void delete(int id)   
	{  
		orderdetailsdao.deleteById(id);  
	}
	public Optional<Order_Details> findById(int orderdetailsId) {
		// TODO Auto-generated method stub
		return orderdetailsdao.findById(orderdetailsId);
	}
}
