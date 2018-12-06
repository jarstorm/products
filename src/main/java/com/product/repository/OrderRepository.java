package com.product.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.model.Order;

/**
 * Order repository.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

	/**
	 * Get order between a ate range
	 * 
	 * @param startDate start date
	 * @param endDate   end date
	 * @return a list of order between these dates
	 */
	@Query("select o from Order o where o.creationDate >= :startDate and o.creationDate < :endDate")
	List<Order> filterByDates(@Param("startDate") Calendar startDate, @Param("endDate") Calendar endDate);

}
