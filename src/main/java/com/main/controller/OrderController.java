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

import com.main.entity.Order_Details;
import com.main.entity.Order_Table;
import com.main.entity.Product;
import com.main.service.OrderDetailService;
import com.main.service.OrderService;
import com.main.service.ProductService;



@RestController
public class OrderController {
	@Autowired
	OrderService orderservice;
	@Autowired
	ProductService productser;
	@Autowired
	OrderDetailService orderdetailsser;

	@RequestMapping(value = "/getAllOrderDetails", method = RequestMethod.GET)
	public ResponseEntity getAllOrderDetails() {
		try {
			List<Order_Table> order = orderservice.getAllOrders();
			if (order.size() == 0)
				return new ResponseEntity<String>("No Records Found", HttpStatus.BAD_REQUEST);
			else
				return new ResponseEntity<List<Order_Table>>(order, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getOrderDetailsById/{Id}", method = RequestMethod.GET)
	public ResponseEntity getOrderDetailsById(@PathVariable("Id") int id) {
		try {
			Order_Table order = orderservice.getOrdersById(id);
			if (order == null) {
				return new ResponseEntity<String>("No Records Found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<Order_Table>(order, HttpStatus.CREATED);
			}
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/updateOrderDetails", method = RequestMethod.PUT)
	public ResponseEntity updateOrderDetails(@RequestBody Order_Table order) {
		try {
			orderservice.saveOrUpdate(order, order.getOrder_Id());
			return new ResponseEntity<Order_Table>(order, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/deleteOrderDetails/{Id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteOrderDetails(@PathVariable("Id") int id) {
		
		try {
			orderservice.delete(id);
			return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/createOrderDetails", method = RequestMethod.POST)
	public ResponseEntity createOrderDetails(@RequestBody Order_Table order) {
		try {
			Product product=productser.getProductDetailsById(order.getProduct_Id());
			if(product.getQuantity()>0) {
			order.setOrder_Status("Confirmed");			
			product.setQuantity(product.getQuantity()-1);
			orderservice.save(order,product);	
			Order_Details orderdetail=new Order_Details();
			orderdetail.setOrder_Id(order.getOrder_Id());
			orderdetail.setProduct_Id(order.getProduct_Id());
			orderdetail.setProduct_price(order.getPayment_Ammount());
			orderdetailsser.save(orderdetail);
			return new ResponseEntity<Order_Table>(order, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Product Out of Stock", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
