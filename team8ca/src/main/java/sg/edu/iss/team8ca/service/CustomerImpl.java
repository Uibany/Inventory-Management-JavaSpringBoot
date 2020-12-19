package sg.edu.iss.team8ca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team8ca.model.Customer;
import sg.edu.iss.team8ca.repo.CustomerRepo;

public class CustomerImpl implements CustomerInterface{
	
	@Autowired
	private CustomerRepo cusrepo;
	
	@Override
	@Transactional
	public void saveCustomer(Customer cus) {
		cusrepo.save(cus);
	}
	
	@Override
	@Transactional
	public void deleteSupplier(Customer cus) {
		cusrepo.delete(cus);
	}
	
	@Override
	@Transactional
	public List<Customer> findAllCustomer(){
		return cusrepo.findAll();
	}
	
	@Override
	@Transactional
	public Customer findCustomerById(long id) {
		return cusrepo.findById(id).get();
	}
	
	@Override
	@Transactional
	public Customer findCustomerByName(String name) {
		return cusrepo.findCusByName(name);	
	}

}
