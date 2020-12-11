package sg.edu.iss.team8ca.service;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.repo.BrandRepo;
import sg.edu.iss.team8ca.repo.CategoryRepo;
import sg.edu.iss.team8ca.repo.InventoryRepo;
import sg.edu.iss.team8ca.repo.SubcategoryRepo;
import sg.edu.iss.team8ca.repo.SupplierRepo;

public class EntryDetailsImpl {
	@Autowired
	BrandRepo brepo;
	CategoryRepo crepo;
	InventoryRepo irepo;
	SubcategoryRepo sRepo;
	SupplierRepo supplierRepo;
	
	public void saveProduct(Inventory inventory) {
		irepo.save(inventory);
	}
	public void createProduct(Inventory inventory) {
		irepo.save(inventory);
	}
	public void updateProduct(Inventory inventory) {
		irepo.save(inventory);
	}
	public void deleteProduct(Inventory inventory) {
		irepo.delete(inventory);
	}

}
