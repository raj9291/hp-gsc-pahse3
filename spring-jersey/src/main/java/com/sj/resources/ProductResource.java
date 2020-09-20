package com.sj.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sj.dto.Product;
import com.sj.service.ManageProductService;

@Component
@Path("/product")
public class ProductResource {
	@Autowired
	private ManageProductService manageProductService;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{productNo}")
	public String getProductPrice(@PathParam("productNo") int productNo) {
		Product product = null;

		product = manageProductService.getProductByProductNo(productNo);
		return String.valueOf(product.getPrice());
	}
}









