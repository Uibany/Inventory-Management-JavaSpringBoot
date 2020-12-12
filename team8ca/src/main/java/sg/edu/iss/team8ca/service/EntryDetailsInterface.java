package sg.edu.iss.team8ca.service;

import java.util.List;

import sg.edu.iss.team8ca.model.Inventory;

public interface EntryDetailsInterface {
	public void createProduct(Inventory inventory);
	public void updateProduct(Inventory inventory);
	public List<Inventory> list();
}
