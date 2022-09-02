package com.kronsoft.internship.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kronsoft.internship.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}

