package com.product.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("select o from Order o where o.creationDate >= :startDate and o.creationDate < :endDate")
	List<Order> filterByDates(@Param("startDate") Calendar startDate, @Param("endDate") Calendar endDate);

}
