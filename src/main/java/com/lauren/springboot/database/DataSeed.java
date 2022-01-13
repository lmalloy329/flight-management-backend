package com.lauren.springboot.database;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.lauren.springboot.model.Customer;
import com.lauren.springboot.repository.CustomerRepository;

public class DataSeed implements CommandLineRunner{
	
	@Autowired
	CustomerRepository custRepo;

	@Override
	public void run(String... args) throws Exception{
		loadCustomerData();
	}
	
	public void loadCustomerData() {
		if(custRepo.count()==0) {
			Set<Customer> customerList = new HashSet<>();
			Customer customer1 = new Customer("Lauren", "Marie", "Malloy", "9176233543", "Lmalloy329@gmail.com", "204-50th ave","Breexy Point", "NY", "USA", "1234567" );
		
			custRepo.save(customer1);
		}
		System.out.println(custRepo.count());
	}

}
