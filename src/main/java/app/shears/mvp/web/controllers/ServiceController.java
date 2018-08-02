package app.shears.mvp.web.controllers;

import app.shears.mvp.models.Service;
import app.shears.mvp.services.api.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {
    private final IServiceService serviceService;

    @Autowired
    public ServiceController(IServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Service> services = serviceService.findAll();

        return new ResponseEntity<>(services, HttpStatus.OK);
    }
}
