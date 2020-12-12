
package sg.edu.iss.team8ca.service;

import java.util.List;

import sg.edu.iss.team8ca.model.Inventory;

public interface EntryDetailsInterface {
	public void saveProduct(Inventory inventory);
	public List<Inventory> list();
}