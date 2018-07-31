package app.shears.mvp.controllers;


import app.shears.mvp.models.Master;
import app.shears.mvp.services.api.IMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

        return new ResponseEntity<>(masters, HttpStatus.OK);
    }
}
