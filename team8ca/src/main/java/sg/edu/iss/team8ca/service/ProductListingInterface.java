package sg.edu.iss.team8ca.service;

import java.util.List;

import sg.edu.iss.team8ca.model.Inventory;

public interface ProductListingInterface {

	public void save(Inventory inventory);

	public List<Inventory> list();

}

