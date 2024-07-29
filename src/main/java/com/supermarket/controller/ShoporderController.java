package com.supermarket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.supermarket.db.ShoporderRepo;
import com.supermarket.model.Shoporder;

@CrossOrigin
@RestController
@RequestMapping("/api/shoporders")
public class ShoporderController {
	
	@Autowired
	private ShoporderRepo shoporderRepo;
	
	@GetMapping("/") // Used for mapping HTTP GET request onto this handler method.
	public List<Shoporder> getAllShoporders() // this method returns List
	{
		return shoporderRepo.findAll();
	}

	@GetMapping("/{id}")
	public Shoporder getAllShoporderById(@PathVariable int id) {
		Optional<Shoporder> u = shoporderRepo.findById(id); // Optional is a data type in Java

		if (u.isPresent()) {
			return u.get(); // select * from Shoporder where id = id
		} else {
			System.err.println("Shoporder[" + id + "] does not exist");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shoporder not found: id [" + id + "]");
		}

	}

	@PostMapping("")
	public Shoporder addShoporder(@RequestBody Shoporder shoporder) {

		return shoporderRepo.save(shoporder);

	}

	@PutMapping("/{id}")
	public Shoporder updateShoporder(@PathVariable int id, @RequestBody Shoporder shoporder) {
		Shoporder u = null;
		if (id != shoporder.getId()) {
			System.err.println("Shoporder Id does not match path id.");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Shoporder not found");
		} else if (!shoporderRepo.existsById(id)) {
			System.err.println("Shoporder does not exist for id: " + id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shoporder does not exist");
		} else
			try {
				u = shoporderRepo.save(shoporder); // update shoporder as body where id = id
			} catch (Exception e) {
				System.err.println(e);
				throw e;
			}
		return u;

	}

	@DeleteMapping("/{id}")
	public boolean deleteShoporder(@PathVariable int id) {
		boolean success = false;

		if (shoporderRepo.existsById(id)) {
			shoporderRepo.deleteById(id);
			success = true;
		} else {
			System.err.println("Delete Error: No Shoporder exists for id: " + id);
			success = false;
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shoporder does not exist");
		}

		return success;
	}


}
