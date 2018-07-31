package app.shears.mvp.services.api;

import app.shears.mvp.models.Order;

import java.util.List;

public interface IOrderService {

    List<Order> findAll();

}
