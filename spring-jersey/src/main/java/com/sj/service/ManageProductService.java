package com.sj.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.dto.Product;
import com.sj.repositories.ProductRepository;

@Service
public class ManageProductService {
	@Autowired
	private ProductRepository productRepository;

	public Product getProductByProductNo(int productNo) {
		Optional<com.sj.entities.Product> optional = null;
		com.sj.entities.Product eProduct = null;
		Product productDto = null;

		optional = productRepository.findById(productNo);
		if (optional.isPresent()) {
			eProduct = optional.get();
			productDto = new Product();
			BeanUtils.copyProperties(eProduct, productDto);
		}
		return productDto;
	}
}
