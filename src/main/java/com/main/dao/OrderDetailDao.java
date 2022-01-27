package com.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.Order_Details;
@Repository
public interface OrderDetailDao extends JpaRepository<Order_Details, Integer> {

}
