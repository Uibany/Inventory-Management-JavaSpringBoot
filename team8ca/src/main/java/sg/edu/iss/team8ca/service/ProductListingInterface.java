package sg.edu.iss.team8ca.service;

import java.util.List;

import sg.edu.iss.team8ca.model.Brand;
import sg.edu.iss.team8ca.model.Category;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.Subcategory;

public interface ProductListingInterface {

	public void saveProduct(Inventory inventory);
	public void editProduct(Inventory inventory);
	public void addProduct(Inventory inventory);
	public void deleteProduct(Inventory inventory);
	
	public Inventory findProductById(Long id);

	public void addBrand(Brand brand);
	public void addSubcategory(Subcategory subcategory);
	
	public List<Inventory> list();
	public List<Brand> listBrand();
	public List<Subcategory> listSubcategory();

	void editProductQuantity(Long id, int newQty);
	
	
}

