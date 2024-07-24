package com.supermarket.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.model.Shoporder;

public interface ShoporderRepo extends JpaRepository<Shoporder, Integer>{

}
