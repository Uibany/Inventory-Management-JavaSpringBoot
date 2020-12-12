package sg.edu.iss.team8ca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.repo.BrandRepo;
import sg.edu.iss.team8ca.repo.CategoryRepo;
import sg.edu.iss.team8ca.repo.InventoryRepo;
import sg.edu.iss.team8ca.repo.SubcategoryRepo;

@Service
public class EntryDetailsImpl implements EntryDetailsInterface {
	@Autowired
	BrandRepo brepo;
	@Autowired
	CategoryRepo crepo;
	@Autowired
	InventoryRepo irepo;
	@Autowired
	SubcategoryRepo sRepo;
	
	
	public EntryDetailsImpl(BrandRepo brepo, CategoryRepo crepo, InventoryRepo irepo, SubcategoryRepo sRepo) {
		super();
		this.brepo = brepo;
		this.crepo = crepo;
		this.irepo = irepo;
		this.sRepo = sRepo;
	}
	@Override
	public void createProduct(Inventory inventory) {
		irepo.save(inventory);
	}
	@Override
	public void updateProduct(Inventory inventory) {
		irepo.save(inventory); //going to work on this
	}
	@Override
	public List<Inventory> list() {
		return irepo.findAll();
	}
}
