package sg.edu.iss.team8ca.service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.team8ca.model.Product;
import sg.edu.iss.team8ca.repo.ProductRepo;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo pRepo;
	
	@Override
	public @NotNull Iterable<Product> getAllProducts() {
		return pRepo.findAll();
	}
	
	@Override
    public Product getProduct(long id) {
        return pRepo
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
 
    @Override
    public Product save(Product product) {
        return pRepo.save(product);
    }

	@Override
	public Product deleteById(long id) {
		Product product = new Product();
		try {
			pRepo.deleteProductByid(id);
		}catch(Exception e){
			return null;
		}
		return product;
	}

}
