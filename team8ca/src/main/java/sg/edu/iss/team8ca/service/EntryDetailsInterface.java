package sg.edu.iss.team8ca.service;

import sg.edu.iss.team8ca.model.Inventory;

public interface EntryDetailsInterface {
	public void save(Inventory inventory);
	public void createProduct(Inventory inventory);
	public void updateProduct(Inventory inventory);
	public void deleteProduct(Inventory inventory);
}
