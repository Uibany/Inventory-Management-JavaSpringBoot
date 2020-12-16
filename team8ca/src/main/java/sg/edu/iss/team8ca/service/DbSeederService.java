package sg.edu.iss.team8ca.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

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

import sg.edu.iss.team8ca.model.TransHistory;
import sg.edu.iss.team8ca.model.TransType;

import sg.edu.iss.team8ca.model.UsageDetails;

import sg.edu.iss.team8ca.model.UsageReportStatus;
import sg.edu.iss.team8ca.model.User;
import sg.edu.iss.team8ca.repo.BrandRepo;
import sg.edu.iss.team8ca.repo.CategoryRepo;
import sg.edu.iss.team8ca.repo.InvUsageRepo;
import sg.edu.iss.team8ca.repo.InventoryRepo;
import sg.edu.iss.team8ca.repo.RoleRepo;
import sg.edu.iss.team8ca.repo.SubcategoryRepo;
import sg.edu.iss.team8ca.repo.SupplierRepo;

import sg.edu.iss.team8ca.repo.TransHistoryRepo;
import sg.edu.iss.team8ca.repo.UsageDetailsRepo;

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
	
	@Autowired
	TransHistoryRepo thRepo;
  
  @Autowired
	UsageDetailsRepo udRepo;


	@Override
	public void run(String... args) throws Exception {
		loadUserData();
		loadSubCategory();
		loadBrand();
		loadInv();		
		loadInvUsage();
		loadTrans();
	}
	
	private String passwordEncoder(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		return encodedPassword;
	}

	private void loadUserData() {
		
		if (userRepo.count() == 0) {
			User user1 = new User("admin", passwordEncoder("password"), "Admin", "User", "admin@gmail.com", "123456", "Jurong East");
			userRepo.save(user1);
			User user2 = new User("sankalp", passwordEncoder("sankalp"), "Sankalp", "  ", "sankalp@gmail.com", "123456", "Jurong West");
			userRepo.save(user2);
			User user3 = new User("team8ca", passwordEncoder("team8ca"), "Team", "JavaCA", "team8@gmail.com", "123456", "Clementi");
			userRepo.save(user3);
			User user4 = new User("Esther", passwordEncoder("asdfasdf"), "Esther", "Tan", "esther@gmail.com", "123456", "Bedok");
			userRepo.save(user4);
		}
	}
	
	private void loadInvUsage() {
		User user1 = userRepo.findByUserName("admin");
		InvUsage invUsage = new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user1);
		iuRepo.save(invUsage);
		Subcategory subcat2 = subcatRepo.findBySubcategoryName("loose parts");
		Brand brand2 = brandRepo.findByBrandName("TonyHawk");
		Inventory inv1 = new Inventory("200 screws", "100 pieces of screws", 10.00,11.00,12.00,13.00,1000,500,200,"Orange","5mm x 1mm",subcat2,brand2);
		Inventory inv2 = new Inventory("Bumper", "attached at front and rear end", 50.00,15.00,60.00,65.00,20,5,1,"Blue","200cm x 40cm",subcat2,brand2);
		Inventory inv3 = new Inventory("Tires", "rubber parts of wheel", 5.00,5.50,6.00,6.50,100,20,4,"Black","26rad",subcat2,brand2);
		Inventory inv4 = new Inventory("Rims", "outer edge of wheel", 20.00,22.00,24.00,26.00,100,20,4,"Silver","25rad",subcat2,brand2);
		invRepo.save(inv1);
		invRepo.save(inv2);
		invRepo.save(inv3);
		invRepo.save(inv4);
		UsageDetails ud = new UsageDetails(inv1, invUsage, LocalDate.now(), 0);
		udRepo.save(ud);
		UsageDetails ud1 = new UsageDetails(inv1, invUsage, LocalDate.now(), 0);
		udRepo.save(ud1);
		UsageDetails ud2 = new UsageDetails(inv1, invUsage, LocalDate.now(), 0);
		udRepo.save(ud2);
		UsageDetails ud3 = new UsageDetails(inv1, invUsage, LocalDate.now(), 0);
		udRepo.save(ud3);		
	}
	
	//category -> subcategory
	private void loadSubCategory() {
		Category cat1 = new Category("Accessories");
		catRepo.save(cat1);
		Subcategory subcat1 = new Subcategory("loose parts", "Accessories", cat1);
		subcatRepo.save(subcat1);
	}
	
	//supplier -> brand
	private void loadBrand() {
		Supplier supplier = new Supplier("Tan Tiong Suppliers", "91232456", "tts@gmail.com", "Tan Tion Avenue" ,312456);
		supRepo.save(supplier);
		Brand brand = new Brand("TonyHawk", "Tony Manufacturing", supplier);
		brandRepo.save(brand);
	}
	
	//brand + subcategory -> inventory
	private void loadInv() {
		Subcategory subcat2 = subcatRepo.findBySubcategoryName("loose parts");
		Brand brand2 = brandRepo.findByBrandName("TonyHawk");
		Inventory inv = new Inventory("100 screws", "100 pieces of screws", 10.00,11.00,12.00,13.00,1000,500,200,"Orange","5mm x 1mm",subcat2,brand2);
		invRepo.save(inv);
	}
	
	private void loadTrans() {
		User user1 = userRepo.findByUserName("admin");
		Inventory inv = invRepo.findInvByName("100 screws");
		TransHistory trans = new TransHistory(TransType.Usage,1,inv,LocalDate.of(2020,11,10), LocalTime.of(21, 30), user1);
		thRepo.save(trans);
	}
	
}
