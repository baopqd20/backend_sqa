package com.example.demo.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }
	
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
	    List<Customer> customers = customerService.findAll();
	    return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        // Check if the customer with given id exists
        if (customerService.findById(id).isPresent()) {
            customerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.findById(id)
                .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
	@PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Optional<Customer> existingCustomerOptional = customerService.findById(id);

        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();

            // Cập nhật tất cả các thuộc tính từ đối tượng cập nhật
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setGender(updatedCustomer.getGender());
            existingCustomer.setDob(updatedCustomer.getDob());
            existingCustomer.setcardId(updatedCustomer.getcardId());
            existingCustomer.setNationality(updatedCustomer.getNationality());
            existingCustomer.setPassport(updatedCustomer.getPassport());
            existingCustomer.setTaxcode(updatedCustomer.getTaxcode());
            existingCustomer.setJob(updatedCustomer.getJob());
            existingCustomer.setPosition(updatedCustomer.getPosition());
            existingCustomer.setPerAddress(updatedCustomer.getPerAddress());
            existingCustomer.setCurAddress(updatedCustomer.getCurAddress());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPhone(updatedCustomer.getPhone());
            // Lưu và đẩy thay đổi xuống cơ sở dữ liệu
            customerService.saveandflush(existingCustomer);

            return new ResponseEntity<>(customerService.saveandflush(existingCustomer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	
}
