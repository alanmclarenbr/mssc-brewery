package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomer(UUID id) {
        return CustomerDto.builder()
                .id(id)
                .name("Alan Santo")
                .build();
    }

    @Override
    public CustomerDto saveNewBeer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        log.debug("Updating customer...");
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        log.debug("Deleting customer...");
    }
}
