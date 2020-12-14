package sg.edu.iss.team8ca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.repo.InventoryRepo;

@Service
public class ProductListingImpl implements ProductListingInterface {

	@Autowired
	InventoryRepo irepo;

	@Override
	@Transactional
	public void save(Inventory inventory) {
		irepo.save(inventory);
	}

	@Override
	public List<Inventory> list() {
		return irepo.findAll();
	}

}
