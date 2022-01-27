package com.main.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.main.entity.Order_Table;
@Repository
public interface OrderDao extends CrudRepository<Order_Table,Integer>{
	@Query("select o from Order_Table o where o.User_Id =:User_Id")
	Collection<Order_Table> orderDetailsBasedOnUser(@Param("User_Id") int User_Id);

}
