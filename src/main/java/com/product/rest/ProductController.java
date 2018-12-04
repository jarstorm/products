package com.product.rest;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.product.exception.ProductException;
import com.product.model.dto.ProductDto;
import com.product.service.ProductService;

@Controller
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDto> getPost() {
		return productService.getAll();
	}

	@PutMapping(value = "/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestParam String name, @RequestParam @NumberFormat(pattern = "####.##") BigDecimal price) {
		productService.createProduct(name, price);
	}

	@PostMapping(value = "/update")
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestParam Long id, @RequestParam String name,
			@RequestParam @NumberFormat(pattern = "####.##") BigDecimal price) throws ProductException {
		productService.updateProduct(id, name, price);
	}

}
