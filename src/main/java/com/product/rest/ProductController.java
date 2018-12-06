package com.product.rest;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.product.exception.ProductException;
import com.product.model.dto.ProductDto;
import com.product.service.ProductService;

/**
 * Product controller.
 */
@Controller
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Request all products
	 * 
	 * @return every product in database
	 */
	@GetMapping(value = "")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDto> getAll() {
		return productService.getAll();
	}

	/**
	 * Create a product.
	 * 
	 * @param name  product name
	 * @param price product price
	 * @return the id of the stored product
	 */
	@PutMapping(value = "/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@RequestParam String name, @RequestParam @NumberFormat(pattern = "####.##") BigDecimal price) {
		return productService.createProduct(name, price);
	}

	/**
	 * Update a product
	 * 
	 * @param id    product id
	 * @param name  new product name
	 * @param price new product price
	 * @throws ProductException exception
	 */
	@PostMapping(value = "/update")
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestParam Long id, @RequestParam String name,
			@RequestParam @NumberFormat(pattern = "####.##") BigDecimal price) throws ProductException {
		productService.updateProduct(id, name, price);
	}

}
