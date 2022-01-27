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
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8936659123825274282L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Product_Id; 	
	@Column  
	private String product_Name;
	@Column  
	private int price;
	@Column  
	private String Product_type;
	@Column  
	private String Product_Description;
	@Column
	private int quantity;
	@Column
	private String Status;
	public int getProduct_Id() {
		return Product_Id;
	}
	public void setProduct_Id(int product_Id) {
		Product_Id = product_Id;
	}
	public String getProduct_Name() {
		return product_Name;
	}
	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getProduct_type() {
		return Product_type;
	}
	public void setProduct_type(String product_type) {
		Product_type = product_type;
	}
	public String getProduct_Description() {
		return Product_Description;
	}
	public void setProduct_Description(String product_Description) {
		Product_Description = product_Description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
}
