package app.shears.mvp.services;

import app.shears.mvp.models.Order;
import app.shears.mvp.repositories.OrderRepository;
import app.shears.mvp.services.api.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOne(Long id) throws Exception {
        Optional<Order> orderOptional = orderRepository.findById(id);

        return orderOptional.orElse(null);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public long count() {
        return orderRepository.count();
    }

    @Override
    public boolean exists(Long id) {
        return orderRepository.existsById(id);
    }
}
