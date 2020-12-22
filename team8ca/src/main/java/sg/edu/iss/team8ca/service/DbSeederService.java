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
import sg.edu.iss.team8ca.model.Fixset;
import sg.edu.iss.team8ca.model.InvUsage;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.Product;
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
import sg.edu.iss.team8ca.repo.FixsetRepo;
import sg.edu.iss.team8ca.repo.InvUsageRepo;
import sg.edu.iss.team8ca.repo.InventoryRepo;
import sg.edu.iss.team8ca.repo.ProductRepo;
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

	@Autowired
	ProductRepo pRepo;

	@Autowired
	FixsetRepo fRepo;

	@Override
	public void run(String... args) throws Exception {
		loadUserData();
		loadSubCategory();
		loadBrand();
		loadCustomer();
		loadInv();
		loadInventory();
		loadInvUsage();
		loadTrans();
		loadProducts();
		loadFixsets();
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
		Category cat9 = new Category("Body");
		catRepo.save(cat1);
		catRepo.save(cat2);
		catRepo.save(cat3);
		catRepo.save(cat4);
		catRepo.save(cat5);
		catRepo.save(cat6);
		catRepo.save(cat7);
		catRepo.save(cat8);
		catRepo.save(cat9);
		Subcategory subcat1 = new Subcategory("Loose parts", "Accessories", cat1);
		Subcategory subcat2 = new Subcategory("Hyundai seat belt", "Seat belt", cat1);
		Subcategory subcat3 = new Subcategory("Toyota airbag", "Airbags", cat4);
		Subcategory subcat4 = new Subcategory("BMW lights", "Head lights", cat3);
		Subcategory subcat5 = new Subcategory("Hyundai windshield", "Windshield", cat6);
		Subcategory subcat6 = new Subcategory("Audi Bodyparts", "Bodyparts", cat8);
		subcatRepo.save(subcat1);
		subcatRepo.save(subcat2);
		subcatRepo.save(subcat3);
		subcatRepo.save(subcat4);
		subcatRepo.save(subcat5);
		subcatRepo.save(subcat6);
	}

	private void loadInvUsage() {
		User user1 = userRepo.findByUserName("admin");
		Customer customer = cusRepo.findAll().get(0);
		InvUsage invUsage = new InvUsage(LocalDate.of(2020, 11, 20), LocalTime.of(13, 10), UsageReportStatus.InProgress,
				customer, user1, "Fixing loose screws");
		iuRepo.save(invUsage);
		Inventory inv1 = invRepo.findInvById(1);
		Inventory inv2 = invRepo.findInvById(2);
		Inventory inv3 = invRepo.findInvById(3);
		Inventory inv4 = invRepo.findInvById(4);
		UsageDetails ud = new UsageDetails(inv1, invUsage, LocalDate.of(2020, 11, 20), LocalTime.of(11, 10), 2);
		udRepo.save(ud);
		UsageDetails ud1 = new UsageDetails(inv2, invUsage, LocalDate.of(2020, 11, 21), LocalTime.of(15, 50), 3);
		udRepo.save(ud1);
		UsageDetails ud2 = new UsageDetails(inv3, invUsage, LocalDate.of(2020, 11, 22), LocalTime.of(9, 45), 4);
		udRepo.save(ud2);
		UsageDetails ud3 = new UsageDetails(inv4, invUsage, LocalDate.of(2020, 11, 23), LocalTime.of(8, 30), 5);
		udRepo.save(ud3);
		TransHistory trans1 = new TransHistory(TransType.Usage, 2, inv1, LocalDate.of(2020, 11, 20),
				LocalTime.of(11, 10), user1);
		TransHistory trans2 = new TransHistory(TransType.Usage, 3, inv2, LocalDate.of(2020, 11, 21),
				LocalTime.of(15, 50), user1);
		TransHistory trans3 = new TransHistory(TransType.Usage, 4, inv3, LocalDate.of(2020, 11, 22),
				LocalTime.of(9, 45), user1);
		TransHistory trans4 = new TransHistory(TransType.Usage, 5, inv4, LocalDate.of(2020, 11, 23),
				LocalTime.of(8, 30), user1);
		thRepo.save(trans1);
		thRepo.save(trans2);
		thRepo.save(trans3);
		thRepo.save(trans4);
	}

	private void loadInventory() {
		Subcategory Tires = subcatRepo.findSubcatByName("loose parts").get(0);
		Subcategory Accessories = subcatRepo.findSubcatByName("loose parts").get(0);
		Subcategory Electrical = subcatRepo.findSubcatByName("BMW lights").get(0);
		Subcategory Windshield = subcatRepo.findSubcatByName("loose parts").get(0);
		Subcategory Body = subcatRepo.findSubcatByName("loose parts").get(0);
		Brand Denso = brandRepo.findBrandByName("Denso Corp").get(0);
		Brand JBL = brandRepo.findBrandByName("JBL").get(0);
		Brand Bosch = brandRepo.findBrandByName("Robert Bosch").get(0);
		Brand Lear = brandRepo.findBrandByName("Lear Corp").get(0);
		Brand Magna = brandRepo.findBrandByName("Magna International").get(0);
		Inventory inv1 = new Inventory("200 screws", "200 pieces of screws", 10.00, 11.00, 12.00, 13.00, 100, 500, 200,
				"Orange", "5mm x 1mm", Accessories, Denso);
		Inventory inv2 = new Inventory("Bumper", "Attached at front/rear end", 50.00, 15.00, 60.00, 65.00, 20, 5, 1,
				"Blue", "200cm x 40cm", Body, Bosch);
		Inventory inv3 = new Inventory("Tires", "Rubber parts of wheel", 5.00, 5.50, 6.00, 6.50, 20, 8, 4, "Black",
				"26rad", Tires, Bosch);
		Inventory inv4 = new Inventory("Rims", "Outer edge of wheel", 20.00, 22.00, 24.00, 26.00, 20, 8, 4, "Silver",
				"25rad", Tires, Bosch);
		Inventory inv5 = new Inventory("Glass", "For windows", 15.00, 17.00, 19.00, 21.00, 20, 8, 1, "Clear",
				"40mx40cm", Windshield, Magna);
		Inventory inv6 = new Inventory("Decklid", "Cover of the trunk", 50.00, 55.00, 60.00, 65.00, 10, 5, 1, "Blue",
				"25rad", Body, Magna);
		Inventory inv7 = new Inventory("Locks", "Cardoor locks", 25.00, 28.00, 30.00, 32.00, 100, 30, 1, "Silver",
				"10mmx10mm", Windshield, Denso);
		Inventory inv8 = new Inventory("Sunroof", "Foldable roof", 180.00, 200.00, 220.00, 240.00, 8, 3, 1,
				"Matt Black", "25rad", Body, Magna);
		Inventory inv9 = new Inventory("Fuel tank", "Container for fuel", 105.00, 110.00, 105.00, 110.00, 20, 10, 1,
				"Silver", "25rad", Body, Magna);
		Inventory inv10 = new Inventory("Video Player", "Plays videos", 80.00, 90.00, 100.00, 110.00, 5, 2, 1, "Black",
				"25rad", Electrical, JBL);
		Inventory inv11 = new Inventory("Subwoofer", "Sounds base", 45.00, 50.00, 53.00, 56.00, 5, 2, 1, "Black",
				"25rad", Electrical, JBL);
		Inventory inv12 = new Inventory("Speaker", "Plays sounds", 30.00, 33.00, 36.00, 38.00, 27, 9, 3, "Black",
				"25rad", Electrical, JBL);
		Inventory inv13 = new Inventory("Car Camera", "Records Video", 115.00, 120.00, 130.00, 135.00, 10, 5, 1,
				"Silver", "25rad", Electrical, JBL);
		Inventory inv14 = new Inventory("Car Battery", "Used to start a vehicle", 25.00, 28.00, 31.00, 33.00, 20, 10, 1,
				"Green", "25rad", Electrical, Magna);
		Inventory inv15 = new Inventory("Tachometer", "Measure rotation speed", 20.00, 22.00, 24.00, 26.00, 20, 10, 1,
				"Black", "25rad", Accessories, Lear);
		Inventory inv16 = new Inventory("Voltmeter", "Measure electric potential", 20.00, 22.00, 24.00, 26.00, 20, 10,
				1, "Black", "25rad", Accessories, Lear);
		Inventory inv17 = new Inventory("Tail Lights", "Car backlights", 5.00, 6.00, 7.00, 8.00, 20, 10, 10, "Yellow",
				"5rad", Accessories, Lear);
		Inventory inv18 = new Inventory("Headlights", "Car frontlights", 20.00, 22.00, 24.00, 26.00, 20, 10, 10,
				"Yellow", "5rad", Accessories, Lear);
		Inventory inv19 = new Inventory("Spotlight", "Bright beam of light", 20.00, 22.00, 24.00, 26.00, 20, 10, 10,
				"White", "5rad", Accessories, Lear);
		Inventory inv20 = new Inventory("Windshield", "Window at the front", 100.00, 120.00, 140.00, 160.00, 20, 10, 10,
				"Black", "1mx0.5m", Windshield, Lear);
		Inventory inv21 = new Inventory("Rear mirror", "Inside mirror", 12.00, 14.00, 16.00, 18.00, 30, 20, 2, "Black",
				"40cmx20cm", Accessories, Magna);
		Inventory inv22 = new Inventory("Side mirror", "Mirror for the sides", 20.00, 22.00, 24.00, 30.00, 20, 10, 2,
				"Black", "20cmx30cm", Accessories, Magna);
		Inventory inv23 = new Inventory("Seatbelts", "For safety", 32.00, 34.00, 36.00, 38.00, 20, 10, 20, "Black",
				"20cmx30cm", Accessories, Magna);
		Inventory inv24 = new Inventory("Engine cover", "Cover for the engine", 120.00, 140.00, 160.00, 180.00, 7, 5, 2,
				"Black", "100cmx30cm", Body, Bosch);
		Inventory inv25 = new Inventory("Engine cover", "Cover for the engine", 120.00, 140.00, 160.00, 180.00, 10, 5,
				2, "White", "100cmx30cm", Body, Bosch);
		Inventory inv26 = new Inventory("Engine cover", "Cover for the engine", 120.00, 140.00, 160.00, 180.00, 9, 5, 2,
				"Red", "100cmx30cm", Body, Bosch);
		Inventory inv27 = new Inventory("Spotlight", "Bright beam of light", 20.00, 22.00, 24.00, 26.00, 20, 10, 10,
				"White", "5rad", Accessories, Magna);
		Inventory inv28 = new Inventory("Locks", "Cardoor locks", 25.00, 28.00, 30.00, 32.00, 100, 30, 1, "Black",
				"10mmx10mm", Accessories, Denso);
		Inventory inv29 = new Inventory("Speaker", "Plays sounds", 30.00, 33.00, 36.00, 38.00, 27, 9, 3, "Silver",
				"25rad", Electrical, Denso);
		Inventory inv30 = new Inventory("Airbag", "For safety by Magna", 80.00, 82.00, 84.00, 86.00, 20, 10, 2, "Black",
				"1unit", Accessories, Magna);
		Inventory inv31 = new Inventory("Airbag", "For safety by Denso", 100.00, 120.00, 140.00, 160.00, 10, 10, 2,
				"Black", "1unit", Accessories, Denso);
		Inventory inv32 = new Inventory("Door", "Black door for car", 200.00, 220.00, 240.00, 300.00, 14, 10, 6,
				"Black", "20cmx30cm", Body, Magna);
		Inventory inv33 = new Inventory("Door", "White door for car", 200.00, 220.00, 240.00, 300.00, 12, 10, 6,
				"White", "20cmx30cm", Body, Lear);
		Inventory inv34 = new Inventory("Door", "Red door for car", 200.00, 220.00, 240.00, 300.00, 5, 10, 6, "Red",
				"20cmx30cm", Body, Bosch);
		Inventory inv35 = new Inventory("Door", "Silver door for car", 200.00, 220.00, 240.00, 300.00, 5, 10, 6,
				"Silver", "20cmx30cm", Body, Bosch);
		Inventory inv36 = new Inventory("Bosch car key", "Window at the front", 20.00, 22.00, 24.00, 30.00, 15, 10, 10,
				"Black", "20cmx30cm", Accessories, Bosch);
		Inventory inv37 = new Inventory("Magna car key", "Window at the front", 20.00, 22.00, 24.00, 30.00, 15, 10, 10,
				"Black", "20cmx30cm", Accessories, Magna);
		Inventory inv38 = new Inventory("Lear car key", "Window at the front", 20.00, 22.00, 24.00, 30.00, 15, 10, 10,
				"Black", "20cmx30cm", Accessories, Lear);
		Inventory inv39 = new Inventory("Car Camera", "Records Video", 115.00, 120.00, 130.00, 135.00, 10, 5, 1,
				"Black", "25rad", Electrical, JBL);
		Inventory inv40 = new Inventory("Black decklid", "Cover of the trunk", 50.00, 55.00, 60.00, 65.00, 10, 5, 1,
				"Black", "25rad", Body, Magna);
		Inventory inv41 = new Inventory("White Decklid", "Cover of the trunk", 50.00, 55.00, 60.00, 65.00, 10, 5, 1,
				"White", "25rad", Body, Magna);
		Inventory inv42 = new Inventory("Red Decklid", "Cover of the trunk", 50.00, 55.00, 60.00, 65.00, 10, 5, 1,
				"Red", "25rad", Body, Magna);
		Inventory inv43 = new Inventory("Side mirror", "Mirror for the sides", 20.00, 22.00, 24.00, 30.00, 20, 10, 2,
				"White", "20cmx30cm", Accessories, Magna);
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
		invRepo.save(inv20);
		invRepo.save(inv21);
		invRepo.save(inv22);
		invRepo.save(inv23);
		invRepo.save(inv24);
		invRepo.save(inv25);
		invRepo.save(inv26);
		invRepo.save(inv27);
		invRepo.save(inv28);
		invRepo.save(inv29);
		invRepo.save(inv30);
		invRepo.save(inv31);
		invRepo.save(inv32);
		invRepo.save(inv33);
		invRepo.save(inv34);
		invRepo.save(inv35);
		invRepo.save(inv36);
		invRepo.save(inv37);
		invRepo.save(inv38);
		invRepo.save(inv39);
		invRepo.save(inv40);
		invRepo.save(inv41);
		invRepo.save(inv42);
		invRepo.save(inv43);
	}

	// supplier -> brand
	private void loadBrand() {
		Supplier supplier = new Supplier("Tan Tiong Suppliers", "91232456", "tts@gmail.com", "Tan Tion Avenue", 312456);
		Supplier supplier1 = new Supplier("Sin Guan Auto Parts", "88170202", "singuan@gmail.com", "67 Veerasamy road",
				661712);
		Supplier supplier2 = new Supplier("Kin Hua Motor Co", "91102034", "kinhua@yahoo.com", "77 Jalan Lembah Kalang",
				014461);
		Supplier supplier3 = new Supplier("He Xing Auto Supply", "80123412", "hexing@gmail.com", "78 Dunlop Street",
				172031);
		Supplier supplier4 = new Supplier("Unico Motor Pte Ltd", "99102233", "unico@gmail.com", "22 Upper Weld road",
				660201);
		Supplier supplier5 = new Supplier("Min Ghee Auto Pte Ltd", "98129345", "minghee@gmail.com", "123 Sin Ming road",
				881023);
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
		Brand brand6 = new Brand("JBL", "JBL Manufacturing", supplier);
		brandRepo.save(brand);
		brandRepo.save(brand1);
		brandRepo.save(brand2);
		brandRepo.save(brand3);
		brandRepo.save(brand4);
		brandRepo.save(brand5);
		brandRepo.save(brand6);
	}

	// brand + subcategory -> inventory
	private void loadInv() {
		Subcategory subcat2 = subcatRepo.findSubcatByName("loose parts").get(0);
		Brand brand2 = brandRepo.findBrandByName("TonyHawk").get(0);
		Inventory inv = new Inventory("100 screws", "100 pieces of screws", 10.00, 11.00, 12.00, 13.00, 1000, 500, 200,
				"Orange", "5mm x 1mm", subcat2, brand2);
		invRepo.save(inv);
	}

	// load new inventory into transHistory
	private void loadTrans() {
		User user1 = userRepo.findByUserName("admin");
		for (long id = 1; id <= invRepo.count(); id++) {
			Inventory inv = invRepo.findInvById(id);
			TransHistory trans = new TransHistory(TransType.NewInventory, inv.getStockQty(), inv,
					LocalDate.of(2020, 11, 10), LocalTime.of(21, 30), user1);
			thRepo.save(trans);
		}
	}

	private void loadCustomer() {
		Customer customer1 = new Customer("Timothy", "+659124526", "Tim@gmail.com", "Ang Mo Kio Avenue 5");
		cusRepo.save(customer1);
		Customer customer2 = new Customer("James", "+6595876245", "James@gmail.com", "Bedok Avenue 2");
		cusRepo.save(customer2);
		Customer customer3 = new Customer("Tilly", "+6594576527", "Tilly@gmail.com", "Orchard Ln 2");
		cusRepo.save(customer3);
		Customer customer4 = new Customer("Sumarah", "+6595467293", "Sumarah@gmail.com", "Grange Road Leornie Hill");
		cusRepo.save(customer4);
		Customer customer5 = new Customer("Jimmy", "+6592467587", "Jimmy@gmail.com", "Toa Payoh Lorong 3");
		cusRepo.save(customer5);
		Customer customer6 = new Customer("Angelo", "+6595246758", "Angelo@gmail.com", "Thomson Avenue 8");
		cusRepo.save(customer6);
		Customer customer7 = new Customer("Shamus", "+6599642348", "Shamus@gmail.com", "Bukit Timah Avenue 2");
		cusRepo.save(customer7);
		Customer customer8 = new Customer("Tricia", "+6595234678", "Tricia@gmail.com", "Ang Mo Kio Avenue 2");
		cusRepo.save(customer8);
		Customer customer9 = new Customer("Ika", "+65954672549", "Ikah@gmail.com", "Tampines Avenue 8");
		cusRepo.save(customer9);
		Customer customer10 = new Customer("King", "+65952346820", "King@gmail.com", "Chinese Garden View");
		cusRepo.save(customer10);
		Customer customer11 = new Customer("Prince", "+6596254203", "Prince@gmail.com", "Jurong Lake View");
		cusRepo.save(customer11);
		Customer customer12 = new Customer("Teriyaki", "+65956485965", "Teriyaki@gmail.com", "Holland Avenue 2");
		cusRepo.save(customer12);
	}

	private void loadProducts() {
		Product product1 = new Product("Tyre", 4);
		Product product2 = new Product("Jack", 1);
		Product product3 = new Product("Wrench", 1);
		Product product4 = new Product("Spanner", 2);
		Product product5 = new Product("Headlight", 2);
		Product product6 = new Product("Screwdriver", 1);
		pRepo.save(product1);
		pRepo.save(product2);
		pRepo.save(product3);
		pRepo.save(product4);
		pRepo.save(product5);
		pRepo.save(product6);
	}

	private void loadFixsets() {
		Product product1 = pRepo.findProductByProductId(1);
		Product product2 = pRepo.findProductByProductId(2);
		Product product3 = pRepo.findProductByProductId(3);
		Product product4 = pRepo.findProductByProductId(4);
		Product product5 = pRepo.findProductByProductId(5);
		Product product6 = pRepo.findProductByProductId(6);
		Fixset fixset1 = new Fixset("Fixset 1", LocalDate.now(), 1, product1);
		Fixset fixset2 = new Fixset("Fixset 1", LocalDate.now(), 1, product2);
		Fixset fixset3 = new Fixset("Fixset 1", LocalDate.now(), 1, product3);
		Fixset fixset4 = new Fixset("Fixset 1", LocalDate.now(), 1, product4);
		Fixset fixset5 = new Fixset("Fixset 2", LocalDate.now(), 1, product5);
		Fixset fixset6 = new Fixset("Fixset 2", LocalDate.now(), 1, product6);
		fRepo.save(fixset1);
		fRepo.save(fixset2);
		fRepo.save(fixset3);
		fRepo.save(fixset4);
		fRepo.save(fixset5);
		fRepo.save(fixset6);
	}

}
