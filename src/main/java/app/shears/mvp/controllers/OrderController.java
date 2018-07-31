package app.shears.mvp.controllers;

import app.shears.mvp.models.Order;
import app.shears.mvp.services.api.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) throws Exception {
        Order order = orderService.findOne(id);
        if (order != null) {
            return new ResponseEntity<>(order, OK);
        } else {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody Order order) {
        orderService.save(order);

        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        orderService.delete(id);

        return new ResponseEntity<>(NO_CONTENT);
    }


    @GetMapping("/count")
    public ResponseEntity<?> count() {
        long count = orderService.count();

        return new ResponseEntity<>(count, OK);
    }


    @GetMapping("/exists")
    public ResponseEntity<?> exists(@RequestParam Long id) throws Exception {
        boolean exists = orderService.exists(id);

        return new ResponseEntity<>(exists, OK);
    }
}
