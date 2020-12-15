package sg.edu.iss.team8ca.service;

import java.util.ArrayList;
import java.util.List;

import sg.edu.iss.team8ca.model.Supplier;

public interface SupplierInterface {

	public boolean saveSupplier(Supplier sup);
	public void deleteSupplier(Supplier sup);
	public List<Supplier> findAllSupplier();
	public Supplier findSupplierById(long id);
	public ArrayList<String> findAllSupplierNames();
}
