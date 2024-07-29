package com.supermarket.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.supermarket.db.ProductRepo;
import com.supermarket.model.Product;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductRepo productRepo;

	@GetMapping("/")
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable int id) {
		Optional<Product> p = productRepo.findById(id);

		if (p.isPresent()) {
			return p.get();
		} else {
			System.err.println("Product[" + id + "]does not exist");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: id [" + id + "]");
		}
	}

	@GetMapping("/fruits")
	public List<Product> getProductByCategory() {
		List<Product> p = new ArrayList<Product>();
		List<Product> fruits = new ArrayList<Product>();

		p = productRepo.findAll();

		for (Product ap : p) {

			if("Fruit".equals(ap.getCategory())){
				fruits.add(ap);
			
				System.out.println("Fruits"+ap.getName());
			
			}
		}
			return fruits;

	}
	@GetMapping("/veggies")
	public List<Product> getProductByCategoryVeggie() {
		List<Product> p = new ArrayList<Product>();
		List<Product> veggies = new ArrayList<Product>();;

		p = productRepo.findAll();

		for (Product ap : p) {

			if("Veggie".equals(ap.getCategory())){
				veggies.add(ap);
			
				System.out.println("Veggie"+ap.getName());
			
			}
		}
			return veggies;

	}
	@GetMapping("/frozen")
	public List<Product> getProductByCategoryFrozen() {
		List<Product> p = new ArrayList<Product>();
		List<Product> frozen = new ArrayList<Product>();

		p = productRepo.findAll();

		for (Product ap : p) {

			if("Frozen".equals(ap.getCategory())){
				frozen.add(ap);
			
				System.out.println("Frozen"+ap.getName());
			
			}
		}
			return frozen;

	}
	@GetMapping("/grocery")
	public List<Product> getProductByGrocery() {
		List<Product> p = new ArrayList<Product>();
		List<Product> grocery = new ArrayList<Product>();

		p = productRepo.findAll();

		for (Product ap : p) {

			if("Grocery".equals(ap.getCategory())){
				grocery.add(ap);
			
				System.out.println("Grocery"+ap.getName());
			
			}
		}
			return grocery;

	}

	@PostMapping("")
	public Product addProduct(@RequestBody Product product) {
		return productRepo.save(product);
	}

	@PutMapping("/{id}")
	public Product UpdateProduct(@PathVariable int id, @RequestBody Product product) {
		Product p = null;
		if (id != product.getId()) {
			System.err.println("RequestId [" + p.getId() + "] does not match pat id[" + id + "].");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
		} else if (!productRepo.existsById(id)) {
			System.err.println("Product does not exist for id: " + id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exist");
		} else
			try {
				p = productRepo.save(product);
			} catch (Exception e) {
				System.err.println(e);
				throw e;
			}
		return p;
	}

	@DeleteMapping("/{id}")
	public boolean deleteProduct(@PathVariable int id) {
		boolean success = false;

		if (productRepo.existsById(id)) {
			productRepo.deleteById(id);
			success = true;
		} else {
			System.err.println("Delete Error: No product exists for id: " + id);
			success = false;
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exist");
		}
		return success;
	}

}
