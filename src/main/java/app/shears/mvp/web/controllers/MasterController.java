package app.shears.mvp.web.controllers;


import app.shears.mvp.models.Master;
import app.shears.mvp.services.api.IMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/master")
public class MasterController {
    private final IMasterService masterService;

    @Autowired
    public MasterController(IMasterService masterService) {
        this.masterService = masterService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Master> masters = masterService.findAll();

        return new ResponseEntity<>(masters, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) throws Exception {
        Master master = masterService.findOne(id);
        if (master != null) {
            return new ResponseEntity<>(master, OK);
        } else {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<?> findOne(@RequestParam String login, @RequestParam String password) throws Exception {
        Master master = masterService.findByLoginAndPassword(login, password);

        return new ResponseEntity<>(master, OK);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody Master master) {
        masterService.save(master);

        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        masterService.delete(id);

        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        long count = masterService.count();

        return new ResponseEntity<>(count, OK);
    }

    @GetMapping("/exists")
    public ResponseEntity<?> exists(@RequestParam String login, @RequestParam String password) throws Exception {
        boolean exists = masterService.existsByLoginAndPassword(login, password);

        return new ResponseEntity<>(exists, OK);
    }
}
