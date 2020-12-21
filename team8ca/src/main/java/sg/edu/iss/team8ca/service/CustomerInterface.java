package sg.edu.iss.team8ca.service;

import java.util.List;

import sg.edu.iss.team8ca.model.Customer;

public interface CustomerInterface {

	public void saveCustomer(Customer cus);
	public void deleteSupplier(Customer cus);
	public List<Customer> findAllCustomer();
	public Customer findCustomerById(long id);
	public Customer findCustomerByNameEmailContactAddress(String name, String email, String contactNo, String address);
	public Customer findCustomerByName(String name);
	public List<Customer> cusSearch(String keyword);
	public void addCustomer (Customer customer);
}
