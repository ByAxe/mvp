package app.shears.mvp.web.controllers;

import app.shears.mvp.models.Customer;
import app.shears.mvp.services.api.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Customer> customers = customerService.findAll();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) throws Exception {
        Customer customer = customerService.findOne(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, OK);
        } else {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        customerService.save(customer);

        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findOne(@RequestParam String login, @RequestParam String password) throws Exception {
        Customer customer = customerService.findByLoginAndPassword(login, password);

        return new ResponseEntity<>(customer, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        customerService.delete(id);

        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        long count = customerService.count();

        return new ResponseEntity<>(count, OK);
    }

    @GetMapping("/exists")
    public ResponseEntity<?> exists(@RequestParam String login, @RequestParam String password) throws Exception {
        boolean exists = customerService.existsByLoginAndPassword(login, password);

        return new ResponseEntity<>(exists, OK);
    }
}
