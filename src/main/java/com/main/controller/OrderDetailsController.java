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
import com.main.service.OrderDetailService;
import com.main.entity.Order_Details;



@RestController
public class OrderDetailsController {
	@Autowired
	OrderDetailService orderservice;

	@RequestMapping(value = "/getAllOrdersDetails", method = RequestMethod.GET)
	public ResponseEntity getAllOrdersDetails() {
		try {
			List<Order_Details> order = orderservice.getAllOrderDetails();
			if (order.size() == 0)
				return new ResponseEntity<String>("No Records Found", HttpStatus.BAD_REQUEST);
			else
				return new ResponseEntity<List<Order_Details>>(order, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getOrdersDetailsById/{Id}", method = RequestMethod.GET)
	public ResponseEntity getOrdersDetailsById(@PathVariable("Id") int id) {
		try {
			Order_Details order = orderservice.getOrderDetailsById(id);
			if (order == null) {
				return new ResponseEntity<String>("No Records Found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<Order_Details>(order, HttpStatus.CREATED);
			}
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/updateOrdersDetails", method = RequestMethod.PUT)
	public ResponseEntity updateOrdersDetails(@RequestBody Order_Details order) {
		try {
			orderservice.saveOrUpdate(order, order.getOrder_Id());
			return new ResponseEntity<Order_Details>(order, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/deleteOrdersDetails/{Id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteOrdersDetails(@PathVariable("Id") int id) {
		try {
			orderservice.delete(id);
			return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/createOrdersDetails", method = RequestMethod.POST)
	public ResponseEntity createOrdersDetails(@RequestBody Order_Details order) {
		try {
			orderservice.save(order);
			return new ResponseEntity<Order_Details>(order, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
