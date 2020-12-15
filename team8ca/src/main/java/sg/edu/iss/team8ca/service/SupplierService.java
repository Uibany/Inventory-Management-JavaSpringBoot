package sg.edu.iss.team8ca.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team8ca.model.Supplier;
import sg.edu.iss.team8ca.repo.SupplierRepo;

@Service
public class SupplierService implements SupplierInterface {

	@Autowired
	SupplierRepo srepo;

	@Transactional

	public boolean saveSupplier(Supplier sup) {
		if (srepo.save(sup) != null)
			return true;
		else
			return false;

	}

	@Transactional
	public void deleteSupplier(Supplier sup) {
		srepo.delete(sup);

	}

	@Transactional
	public List<Supplier> findAllSupplier() {
		return srepo.findAll();
	}

	@Transactional
	public Supplier findSupplierById(long id) {
		return srepo.findById(id).get();
	}

	@Transactional
	public ArrayList<String> findAllSupplierNames() {
		List<Supplier> suppliers = srepo.findAll();
		ArrayList<String> names = new ArrayList<String>();
		for (Iterator<Supplier> iterator = suppliers.iterator(); iterator.hasNext();) {
			Supplier supplier = (Supplier) iterator.next();
			names.add(supplier.getCompanyName());
		}
		return names;
	}

}
