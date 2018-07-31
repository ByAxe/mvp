package app.shears.mvp.controllers;

import app.shears.mvp.models.Order;
import app.shears.mvp.services.api.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Order> orders = orderService.findAll();

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
