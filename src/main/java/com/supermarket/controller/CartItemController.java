package com.supermarket.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.supermarket.db.CartItemRepo;
import com.supermarket.db.CartRepo;
import com.supermarket.db.UserRepo;
import com.supermarket.model.Cart;
import com.supermarket.model.CartItem;
import com.supermarket.model.Product;

@CrossOrigin
@RestController
@RequestMapping("/api/cartitems")
public class CartItemController {

	@Autowired
	private CartItemRepo cartItemRepo;
	
	@Autowired
	private CartRepo cartRepo;
	
	@GetMapping("/")
	public List<CartItem> getAllCartItems()
	{
		return cartItemRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public CartItem getCartItemById(@PathVariable int id) {
		Optional<CartItem> c = cartItemRepo.findById(id);
		
		if(c.isPresent()) {
			return c.get();
		}
		else {
			System.err.println("CartItem["+id+"] does not exist");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cartitem not found: id["+id+"]");
		}
	}
	
	@GetMapping("/getcartitemsforcart/{id}")
	public List<CartItem> getCartItemsForCart(@PathVariable int id)
	{
		Optional<Cart> crt = cartRepo.findById(id);
		List<CartItem> cartItemsForCart = new ArrayList<CartItem>();
		
		if(crt.isPresent()) {
			
			Cart c = crt.get();
			cartItemsForCart = cartItemRepo.findByCart(c);
			
		}
		else
			System.out.println("CartItem does not exist");
		
		return cartItemsForCart;
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("")
	public CartItem addCartItem(@RequestBody CartItem cartItem) {
		CartItem l1 = null;
		Cart cr = null;
		System.out.println("saving line item: "+cartItem);
		l1 = cartItemRepo.save(cartItem);// insert into cartItem from body
		cr = cartRepo.getById(l1.getCart().getId());
		recalculateCartTotal(cr);
		cartItemRepo.save(l1);
		return l1;
	}

	@PutMapping("/{id}")
	public CartItem updateCartItem(@PathVariable int id, @RequestBody CartItem cartItem) {
		CartItem l = null;
		if (id != cartItem.getId()) {
			System.err.println("CartItem Id does not match path id.");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CartItem not found");
		} else if (!cartItemRepo.existsById(id)) {
			System.err.println("CartItem does not exist for id: " + id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CartItem does not exist");
		} else
			try {
				Cart cr1 = cartItem.getCart();
				l = cartItemRepo.save(cartItem);
				recalculateCartTotal(cr1);
				// update cartItem as body where id = id
			} catch (Exception e) {
				System.err.println(e);
				throw e;
			}
		System.out.println("CartItemId after update "+l.getId());
		return l;

	}

	@SuppressWarnings({ "deprecation" })
	@DeleteMapping("/{id}")
	public boolean deleteCartItem(@PathVariable int id) {
		boolean success = false;

		CartItem l = null;
		if (cartItemRepo.existsById(id)) {
			l = cartItemRepo.getById(id);
			Cart crt = l.getCart();
			cartItemRepo.deleteById(id);
			recalculateCartTotal(crt);
			success = true;
		}

		else {
			System.err.println("Delete Error: No cartItem exists for id: " + id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CartItem does not exist");
		}

		return success;
	}

	private void recalculateCartTotal(Cart cr) {
		float sum = 0;
		
		for (CartItem c1: cartItemRepo.findByCart(cr)) {
			
			Product product = c1.getProduct();
			
			int qty = product.getQuantity();
			
			float priceOfCartProduct = product.getPrice();
			
			System.out.println("Quantity = "+product.getQuantity()+ "Price = "+product.getPrice());
			
			sum+= (qty * priceOfCartProduct);
			System.out.println("Total = "+sum);
		}
		
		cr.setTotalAmount(sum);
		cartRepo.save(cr);
		
	}
	
	
}
