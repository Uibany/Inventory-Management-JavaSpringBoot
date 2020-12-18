package sg.edu.iss.team8ca.service;

import java.util.ArrayList;
import java.util.List;

import sg.edu.iss.team8ca.model.Supplier;
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
	public void addCategory(Category category);
	public void addSupplier(Supplier supplier);
	
	public List<Inventory> list();
	public List<Brand> listBrand();
	public List<Subcategory> listSubcategory();
	public ArrayList<String> findAllBrandNames();
	public ArrayList<String> findAllSubcatNames();
	public Brand findBrandByName(String name);
	public Subcategory findSubcatByName(String name);
	public List<Category> listCategory();
	public ArrayList<String> findAllCategoryNames();
	public Category findCatByName(String name);
	public List<Supplier> listSupplier();
	public ArrayList<String> findAllSupplierNames();
	public Supplier findSupplierByName(String name);
	
	public void editProductQuantity(Long id, int newQty);
	public Inventory getProduct(long id);
	
	
}

