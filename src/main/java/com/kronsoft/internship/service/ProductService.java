package com.kronsoft.internship.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.kronsoft.internship.dao.ProductRepository;
import com.kronsoft.internship.entities.Product;

@Service
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	private void populateProductList() {
		try {
			if (productRepository.count() == 0) {
				Resource resource = new ClassPathResource("products.csv");
				CsvSchema schema = CsvSchema.emptySchema().withHeader();
				CsvMapper mapper = new CsvMapper();
				MappingIterator<Product> iterator = mapper.readerFor(Product.class).with(schema)
						.readValues(resource.getInputStream());
				List<Product> products = productRepository.saveAll(iterator.readAll());
			}
		} catch (IOException e) {
			logger.error("An error occurred while populating products.", e);
		}
	}

}
