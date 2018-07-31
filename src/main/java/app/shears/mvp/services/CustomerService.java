package app.shears.mvp.services;

import app.shears.mvp.models.Customer;
import app.shears.mvp.repositories.CustomerRepository;
import app.shears.mvp.services.api.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findOne(Long id) throws Exception {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        return customerOptional.orElse(null);
    }

    @Override
    public void save(Customer master) {
        customerRepository.save(master);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public long count() {
        return customerRepository.count();
    }

    @Override
    public boolean existsByLogin(String login) {
        return customerRepository.existsByLogin(login);
    }

    @Override
    public boolean existsByLoginAndPassword(String login, String password) {
        return customerRepository.existsByLoginAndPassword(login, password);
    }

    @Override
    public Customer findByLoginAndPassword(String login, String password) {
        return customerRepository.findByLoginAndPassword(login, password);
    }
}
