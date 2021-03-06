package app.shears.mvp.services;

import app.shears.mvp.models.Order;
import app.shears.mvp.quartz.JobInitializer;
import app.shears.mvp.repositories.OrderRepository;
import app.shears.mvp.services.api.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    private final JobInitializer jobInitializer;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, JobInitializer jobInitializer) {
        this.orderRepository = orderRepository;
        this.jobInitializer = jobInitializer;
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

    public void placeAnOrder(Order order) throws Exception {
        save(order);

        // Create quartz job
        jobInitializer.placeOrderJob(order);
    }

    @Override
    public void cancel(Long id) {
        // Remove quartz job
        jobInitializer.removeJobGroup(id);
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
