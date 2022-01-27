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

import com.main.entity.Product;
import com.main.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	ProductService productservice;

	@RequestMapping(value = "/getAllProductDetails", method = RequestMethod.GET)
	public ResponseEntity getAllProductDetails() {
		try {
			List<Product> product = productservice.getAllProductDetails();
			if (product.size() == 0)
				return new ResponseEntity<String>("No Records Found", HttpStatus.BAD_REQUEST);
			else
				return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getProductDetailsById/{Id}", method = RequestMethod.GET)
	public ResponseEntity getProductDetailsById(@PathVariable("Id") int id) {
		try {
			Product product = productservice.getProductDetailsById(id);
			if (product == null) {
				return new ResponseEntity<String>("No Records Found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<Product>(product, HttpStatus.CREATED);
			}
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/updateProductDetails", method = RequestMethod.PUT)
	public ResponseEntity updateProductDetails(@RequestBody Product product) {
		try {
			productservice.saveOrUpdate(product, product.getProduct_Id());
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/deleteProductDetails/{Id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteProductDetails(@PathVariable("Id") int id) {
		try {
			productservice.delete(id);
			return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/createProductDetails", method = RequestMethod.POST)
	public ResponseEntity createProductDetails(@RequestBody Product product) {
		try {
			productservice.save(product);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
