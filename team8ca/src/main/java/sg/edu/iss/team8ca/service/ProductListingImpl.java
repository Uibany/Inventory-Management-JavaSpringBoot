package sg.edu.iss.team8ca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.repo.InventoryRepo;

public class ProductListingImpl implements ProductListingInterface {

	
	@Autowired 
	InventoryRepo irepo;
	
	@Override
	public void save(Inventory inventory) {
		irepo.save(inventory);
		
	}

	@Override
 	public List<Inventory> list() {
		return irepo.findAll();
	}
}
