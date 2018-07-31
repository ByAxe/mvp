package app.shears.mvp.services.api;

import app.shears.mvp.models.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> findAll();

    Customer findOne(Long id) throws Exception;

    void save(Customer master);

    void delete(Long id);

    long count();

    boolean existsByLogin(String login);

    boolean existsByLoginAndPassword(String login, String password);

    Customer findByLoginAndPassword(String login, String password);
}
