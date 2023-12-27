package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId) {
        return new ResponseEntity<>(customerService.getCustomer(customerId), OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto savedDto = customerService.saveNewBeer(customerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedDto.getId());

        return new ResponseEntity<>(headers, CREATED);
    }

    @PutMapping({"/{customerId}"})
    public ResponseEntity<CustomerDto> handleUpdate(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(customerId, customerDto);

        return new ResponseEntity<>(NO_CONTENT);
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
        customerService.deleteCustomer(customerId);
    }

}
