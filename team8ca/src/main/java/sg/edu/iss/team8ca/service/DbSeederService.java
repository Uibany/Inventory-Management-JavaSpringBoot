package sg.edu.iss.team8ca.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import sg.edu.iss.team8ca.model.Brand;
import sg.edu.iss.team8ca.model.Category;
import sg.edu.iss.team8ca.model.Customer;
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
import sg.edu.iss.team8ca.repo.CustomerRepo;
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

	@Autowired
	CustomerRepo cusRepo;

	@Override
	public void run(String... args) throws Exception {
		loadUserData();
		loadSubCategory();
		loadBrand();
		loadInv();
		loadInvUsage();
		loadTrans();
		addCustomer();
	}

	private String passwordEncoder(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		return encodedPassword;
	}

	private void loadUserData() {

		if (userRepo.count() == 0) {
			User user1 = new User("admin", passwordEncoder("password"), "Admin", "User", "admin@gmail.com", "123456",
					"Jurong East");
			userRepo.save(user1);
			User user2 = new User("sankalp", passwordEncoder("sankalp"), "Sankalp", "  ", "sankalp@gmail.com", "123456",
					"Jurong West");
			userRepo.save(user2);
			User user3 = new User("team8ca", passwordEncoder("team8ca"), "Team", "JavaCA", "team8@gmail.com", "123456",
					"Clementi");
			userRepo.save(user3);
			User user4 = new User("Esther", passwordEncoder("asdfasdf"), "Esther", "Tan", "esther@gmail.com", "123456",
					"Bedok");
			userRepo.save(user4);
		}
	}

	private void loadInvUsage() {
		User user1 = userRepo.findByUserName("admin");
		InvUsage invUsage = new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user1, "Fixing loose screws");
		iuRepo.save(invUsage);
		Subcategory subcat2 = subcatRepo.findSubcatByName("loose parts").get(0);
		Brand brand2 = brandRepo.findBrandByName("TonyHawk").get(0);
		Inventory inv1 = new Inventory("200 screws", "100 pieces of screws", 10.00, 11.00, 12.00, 13.00, 100, 500, 200,
				"Orange", "5mm x 1mm", subcat2, brand2);
		Inventory inv2 = new Inventory("Bumper", "attached at front and rear end", 50.00, 15.00, 60.00, 65.00, 20, 5, 1,
				"Blue", "200cm x 40cm", subcat2, brand2);
		Inventory inv3 = new Inventory("Tires", "rubber parts of wheel", 5.00, 5.50, 6.00, 6.50, 10, 20, 4, "Black",
				"26rad", subcat2, brand2);
		Inventory inv4 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv5 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv6 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv7 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv8 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv9 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv10 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv11 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv12 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv13 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv14 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv15 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv16 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv17 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv18 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		Inventory inv19 = new Inventory("Rims", "outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 10, 20, 4, "Silver",
				"25rad", subcat2, brand2);
		invRepo.save(inv1);
		invRepo.save(inv2);
		invRepo.save(inv3);
		invRepo.save(inv4);
		invRepo.save(inv5);
		invRepo.save(inv6);
		invRepo.save(inv7);
		invRepo.save(inv8);
		invRepo.save(inv9);
		invRepo.save(inv10);
		invRepo.save(inv11);
		invRepo.save(inv12);
		invRepo.save(inv13);
		invRepo.save(inv14);
		invRepo.save(inv15);
		invRepo.save(inv16);
		invRepo.save(inv17);
		invRepo.save(inv18);
		invRepo.save(inv19);
		UsageDetails ud = new UsageDetails(inv1, invUsage, LocalDate.now(), 0);
		udRepo.save(ud);
		UsageDetails ud1 = new UsageDetails(inv1, invUsage, LocalDate.now(), 0);
		udRepo.save(ud1);
		UsageDetails ud2 = new UsageDetails(inv1, invUsage, LocalDate.now(), 0);
		udRepo.save(ud2);
		UsageDetails ud3 = new UsageDetails(inv1, invUsage, LocalDate.now(), 0);
		udRepo.save(ud3);
	}

	// category -> subcategory
	private void loadSubCategory() {
		Category cat1 = new Category("Accessories");
		Category cat2 = new Category("Tires");
		Category cat3 = new Category("Electrical");
		Category cat4 = new Category("Airbags");
		Category cat5 = new Category("Fuel Guage");
		Category cat6 = new Category("Windshield");
		Category cat7 = new Category("Vehicle Frame");
		Category cat8 = new Category("Speedometer");
		catRepo.save(cat1);
		catRepo.save(cat2);
		catRepo.save(cat3);
		catRepo.save(cat4);
		catRepo.save(cat5);
		catRepo.save(cat6);
		catRepo.save(cat7);
		catRepo.save(cat8);
		Subcategory subcat1 = new Subcategory("loose parts", "Accessories", cat1);
		Subcategory subcat2 = new Subcategory("Hyundai seat belt", "Seat belt", cat1);
		Subcategory subcat3 = new Subcategory("Toyota airbag", "Airbags", cat4);
		Subcategory subcat4 = new Subcategory("BMW lights", "Head lights", cat3);
		Subcategory subcat5 = new Subcategory("Hyundai windshield", "Windshield", cat6);
		subcatRepo.save(subcat1);
		subcatRepo.save(subcat2);
		subcatRepo.save(subcat3);
		subcatRepo.save(subcat4);
		subcatRepo.save(subcat5);
	}

	// supplier -> brand
	private void loadBrand() {
		Supplier supplier = new Supplier("Tan Tiong Suppliers", "91232456", "tts@gmail.com", "Tan Tion Avenue", 312456);
		Supplier supplier1 = new Supplier("Sin Guan Auto Parts", "88170202", "singuan@gmail.com", "67 Veerasamy road", 661712);
		Supplier supplier2 = new Supplier("Kin Hua Motor Co", "91102034", "kinhua@yahoo.com", "77 Jalan Lembah Kalang", 014461);
		Supplier supplier3 = new Supplier("He Xing Auto Supply", "80123412", "hexing@gmail.com", "78 Dunlop Street", 172031);
		Supplier supplier4 = new Supplier("Unico Motor Pte Ltd", "99102233", "unico@gmail.com", "22 Upper Weld road", 660201);
		Supplier supplier5 = new Supplier("Min Ghee Auto Pte Ltd", "98129345", "minghee@gmail.com", "123 Sin Ming road", 881023);
		supRepo.save(supplier);
		supRepo.save(supplier1);
		supRepo.save(supplier2);
		supRepo.save(supplier3);
		supRepo.save(supplier4);
		supRepo.save(supplier5);
		Brand brand = new Brand("TonyHawk", "Tony Manufacturing", supplier);
		Brand brand1 = new Brand("Robert Bosch", "Bosch Manufacturing", supplier1);
		Brand brand2 = new Brand("Denso Corp", "Denso Manufacturing", supplier2);
		Brand brand3 = new Brand("Magna International", "Magna Manufacturing", supplier3);
		Brand brand4 = new Brand("Lear Corp", "Lear Manufacturing", supplier1);
		Brand brand5 = new Brand("AC Delco", "Delco Manufacturing", supplier);
		brandRepo.save(brand);
		brandRepo.save(brand1);
		brandRepo.save(brand2);
		brandRepo.save(brand3);
		brandRepo.save(brand4);
		brandRepo.save(brand5);
	}

	// brand + subcategory -> inventory
	private void loadInv() {
		Subcategory subcat2 = subcatRepo.findSubcatByName("loose parts").get(0);
		Brand brand2 = brandRepo.findBrandByName("TonyHawk").get(0);
		Inventory inv = new Inventory("100 screws", "100 pieces of screws", 10.00, 11.00, 12.00, 13.00, 1000, 500, 200,
				"Orange", "5mm x 1mm", subcat2, brand2);
		invRepo.save(inv);
	}

	private void loadTrans() {
		User user1 = userRepo.findByUserName("admin");
		Inventory inv = invRepo.findInvByName("100 screws");
		TransHistory trans = new TransHistory(TransType.Usage, 1, inv, LocalDate.of(2020, 11, 10), LocalTime.of(21, 30),
				user1);
		thRepo.save(trans);
	}

	private void addCustomer() {
		Customer customer1 = new Customer("Timothy", "+659124526", "Tim@gmail.com", "Ang Mo Kio Avenue 5", 125468);
		cusRepo.save(customer1);
		Customer customer2 = new Customer("James", "+6595876245", "James@gmail.com", "Bedok Avenue 2", 246859);
		cusRepo.save(customer2);
	}

}
