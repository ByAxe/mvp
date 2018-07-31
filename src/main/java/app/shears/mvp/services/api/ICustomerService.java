package app.shears.mvp.services.api;

import app.shears.mvp.models.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> findAll();

}
