package sg.edu.iss.team8ca.service;

import java.util.List;

import sg.edu.iss.team8ca.model.Inventory;

public interface ProductListingInterface {

	public void saveProduct(Inventory inventory);

	public List<Inventory> list();

	public void deleteProduct(Inventory inventory);
	
	public Inventory findProductById(Long id);
	
	public void editProduct(Inventory inventory);

}

