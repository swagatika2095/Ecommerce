package com.main.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Order_Table implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Order_Id; 
	@Column
	private int User_Id;  
	@Column
	private int Product_Id;  
	@Column
	public String Order_Status;
	@Column
	public String Payment_Type;
	@Column  
	private int Payment_Ammount;
	public int getOrder_Id() {
		return Order_Id;
	}
	public void setOrder_Id(int order_Id) {
		Order_Id = order_Id;
	}
	public int getUser_Id() {
		return User_Id;
	}
	public void setUser_Id(int user_Id) {
		User_Id = user_Id;
	}
	
	public int getProduct_Id() {
		return Product_Id;
	}
	public void setProduct_Id(int product_Id) {
		Product_Id = product_Id;
	}
	public String getOrder_Status() {
		return Order_Status;
	}
	public void setOrder_Status(String order_Status) {
		Order_Status = order_Status;
	}
	public String getPayment_Type() {
		return Payment_Type;
	}
	public void setPayment_Type(String payment_Type) {
		Payment_Type = payment_Type;
	}
	public int getPayment_Ammount() {
		return Payment_Ammount;
	}
	public void setPayment_Ammount(int payment_Ammount) {
		Payment_Ammount = payment_Ammount;
	}

	

}
