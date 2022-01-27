package com.main.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dao.OrderDao;
import com.main.entity.Order_Table;
import com.main.entity.Product;
@Service
public class OrderService {

	@Autowired
	OrderDao orderdao;
	@Autowired
	ProductService productser;
	
	public List<Order_Table> getAllOrders(){
		List<Order_Table> order = new ArrayList<Order_Table>();  
		orderdao.findAll().forEach(order1 -> order.add(order1)); 
		return order;  
	}
	public  Order_Table getOrdersById(int id)   
	{  
	return orderdao.findById(id).get();  
	} 
	public  void saveOrUpdate(Order_Table order,int orderId)   
	{  
		orderdao.save(order);  
	}
	@Transactional
	public  void save(Order_Table order,Product product)   
	{  
		orderdao.save(order);  
		productser.saveOrUpdate(product, product.getProduct_Id());
		
	} 
	public  void delete(int id)   
	{  
		orderdao.deleteById(id);  
	}
	public Optional<Order_Table> findById(int orderId) {
		// TODO Auto-generated method stub
		return orderdao.findById(orderId);
	}
	public Collection<Order_Table> orderDetailsBasedOnUser(int userid){
		return orderdao.orderDetailsBasedOnUser(userid);
		
	}

}
