package sg.edu.iss.team8ca.service;

import javax.validation.constraints.NotNull;

import sg.edu.iss.team8ca.model.Product;

public interface ProductService {
	
	@NotNull
	Iterable<Product> getAllProducts();

	Product getProduct(long id);

	Product save(Product product);

	Product deleteById(long id);

}
