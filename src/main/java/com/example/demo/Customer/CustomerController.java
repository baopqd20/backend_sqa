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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody @Valid CustomerRequest request) {
        Customer customer = Customer.builder()
                .cardId(request.getIdentify())
                .curAddress(request.getCurAddress())
                .perAddress(request.getPerAddress())
                .passport(request.getPassport())
                .email(request.getEmail())
                .dob(request.getDob())
                .position(request.getPosition())
                .job(request.getJob())
                .phone(request.getPhone_number())
                .gender(request.getGender())
                .nationality(request.getNationality())
                .build();
        Customer savedCustomer = customerService.save(customer);
        CustomerResponse response = CustomerResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Create customer successfully")
                .data(savedCustomer)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchCustomer(@RequestParam String username) {
        CustomerResponse response = CustomerResponse.builder()
                .status(HttpStatus.OK.value())
                .message("searched successfully")
                .data(customerService.searchCustomer(username))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
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
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerRequest updatedCustomer) {
        Optional<Customer> existingCustomerOptional = customerService.findById(id);

        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();

            // Cập nhật tất cả các thuộc tính từ đối tượng cập nhật
            existingCustomer.setName(updatedCustomer.getFull_name());
            existingCustomer.setGender(updatedCustomer.getGender());
            existingCustomer.setDob(updatedCustomer.getDob());
            existingCustomer.setCardId(updatedCustomer.getIdentify());
            existingCustomer.setNationality(updatedCustomer.getNationality());
            existingCustomer.setPassport(updatedCustomer.getPassport());
            existingCustomer.setTaxcode(updatedCustomer.getTaxcode());
            existingCustomer.setJob(updatedCustomer.getJob());
            existingCustomer.setPosition(updatedCustomer.getPosition());
            existingCustomer.setPerAddress(updatedCustomer.getPerAddress());
            existingCustomer.setCurAddress(updatedCustomer.getCurAddress());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPhone(updatedCustomer.getPhone_number());
            // Lưu và đẩy thay đổi xuống cơ sở dữ liệu
            customerService.saveandflush(existingCustomer);

            return new ResponseEntity<>(customerService.saveandflush(existingCustomer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
