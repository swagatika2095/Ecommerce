package com.main.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@DynamicUpdate
public class Order_Details implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2912095093255256188L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Order_Details_Id;
	@Column
	private int Order_Id; 	
	@Column  
	private int Product_Id;
	@Column
	private int product_price;
	public int getOrder_Id() {
		return Order_Id;
	}
	public void setOrder_Id(int order_Id) {
		Order_Id = order_Id;
	}
	public int getProduct_Id() {
		return Product_Id;
	}
	public void setProduct_Id(int product_Id) {
		Product_Id = product_Id;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getOrder_Details_Id() {
		return Order_Details_Id;
	}
	public void setOrder_Details_Id(int order_Details_Id) {
		Order_Details_Id = order_Details_Id;
	}
	
	
	
	

}
