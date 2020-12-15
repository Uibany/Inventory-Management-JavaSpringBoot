package sg.edu.iss.team8ca.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import sg.edu.iss.team8ca.model.Brand;
import sg.edu.iss.team8ca.model.Category;
import sg.edu.iss.team8ca.model.InvUsage;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.Subcategory;
import sg.edu.iss.team8ca.model.Supplier;
import sg.edu.iss.team8ca.model.UsageReportStatus;
import sg.edu.iss.team8ca.model.User;
import sg.edu.iss.team8ca.repo.BrandRepo;
import sg.edu.iss.team8ca.repo.CategoryRepo;
import sg.edu.iss.team8ca.repo.InvUsageRepo;
import sg.edu.iss.team8ca.repo.InventoryRepo;
import sg.edu.iss.team8ca.repo.RoleRepo;
import sg.edu.iss.team8ca.repo.SubcategoryRepo;
import sg.edu.iss.team8ca.repo.SupplierRepo;
import sg.edu.iss.team8ca.repo.UserRepo;

@Component
public class DbSeederService implements CommandLineRunner {

	@Autowired
	UserRepo userRepo;
	
	@Autowired	
	RoleRepo roleRepo;
	
	@Autowired	
	InvUsageRepo iuRepo;
	
	@Autowired
	CategoryRepo catRepo;
	
	@Autowired
	BrandRepo brandRepo;
	
	@Autowired
	SubcategoryRepo subcatRepo;
	
	@Autowired
	SupplierRepo supRepo;
	
	@Autowired
	InventoryRepo invRepo;

	@Override
	public void run(String... args) throws Exception {
		loadUserData();
		loadSubCategory();
		loadBrand();
		loadInv();		
		loadInvUsage();
	}

	private void loadUserData() {
		String password = "password";
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(password);

		if (userRepo.count() == 0) {
			User user1 = new User("admin", encodedPassword);
			userRepo.save(user1);
		}
		System.out.println(userRepo.count());
	}
	
	private void loadInvUsage() {
		User user1 = userRepo.findByUserName("admin");
		InvUsage invUsage = new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user1);
		iuRepo.save(invUsage);
	}
	
	private void loadSubCategory() {
		Category cat1 = new Category("Accessories");
		catRepo.save(cat1);
		Subcategory subcat1 = new Subcategory("loose parts", "Accessories", cat1);
		subcatRepo.save(subcat1);
	}
	
	private void loadBrand() {
		Supplier supplier = new Supplier("Tan Tiong Suppliers", "91232456", "tts@gmail.com", "Tan Tion Avenue" ,312456);
		supRepo.save(supplier);
		Brand brand = new Brand("TonyHawk", "Tony Manufacturing", supplier);
		brandRepo.save(brand);
	}
	
	private void loadInv() {
		Subcategory subcat2 = subcatRepo.findBySubcategoryName("loose parts");
		Brand brand2 = brandRepo.findByBrandName("TonyHawk");
		Inventory inv = new Inventory("100 screws", "100 pieces of screws", 10.00,11.00,12.00,13.00,1000,500,200,"Orange","5mm x 1mm",subcat2,brand2);
		invRepo.save(inv);
	}
	
}
