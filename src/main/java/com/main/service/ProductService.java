package com.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dao.ProductDao;
import com.main.entity.Product;

@Service
public class ProductService{


	@Autowired
	ProductDao productdao;
	
	public List<Product> getAllProductDetails(){
		List<Product> product = new ArrayList<Product>();  
		productdao.findAll().forEach(product1 -> product.add(product1)); 
		return product;  
	}
	public  Product getProductDetailsById(int id)   
	{  
	return productdao.findById(id).get();  
	} 
	public  void saveOrUpdate(Product product,int productId)   
	{  
		productdao.save(product);  
	}
	public  void save(Product product)   
	{  
		productdao.save(product);  
	} 
	public  void delete(int id)   
	{  
		productdao.deleteById(id);  
	}
	public Optional<Product> findById(int productId) {
		// TODO Auto-generated method stub
		return productdao.findById(productId);
	}



}
