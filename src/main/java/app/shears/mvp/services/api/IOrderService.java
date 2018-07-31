package app.shears.mvp.services.api;

import app.shears.mvp.models.Order;

import java.util.List;

public interface IOrderService {

    List<Order> findAll();

    Order findOne(Long id) throws Exception;

    void save(Order order);

    void delete(Long id);

    long count();

    boolean exists(Long id);

    void placeAnOrder(Order order) throws Exception;
}
