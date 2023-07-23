package com.interview.app.rest.controller;

import com.interview.app.rest.exception.ResourceNotFoundException;
import com.interview.app.rest.model.Manufacturer;
import com.interview.app.rest.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    //read all
    @GetMapping
    public List<Manufacturer> getAllManufacturers(){
        return manufacturerRepository.findAll();
    }

    //post
    @PostMapping
    public Manufacturer createManufacturer(@RequestBody Manufacturer manufacturer){
        return manufacturerRepository.save(manufacturer);
    }

    //read by ID
    @GetMapping("{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable long id){
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer with id:" + id + "does not exist"));
         return ResponseEntity.ok(manufacturer);
    }

    //update
    @PutMapping("{id}")
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable long id, @RequestBody Manufacturer manufacturerProperties) {
        Manufacturer updateManufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer with id:" + id + "does not exist"));
        updateManufacturer.setName(manufacturerProperties.getName());
        updateManufacturer.setStreet(manufacturerProperties.getStreet());
        updateManufacturer.setCity(manufacturerProperties.getCity());
        updateManufacturer.setZipCode(manufacturerProperties.getZipCode());
        updateManufacturer.setCountry(manufacturerProperties.getCountry());

        manufacturerRepository.save(updateManufacturer);

        return ResponseEntity.ok(updateManufacturer);
    }

    //delete
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteManufacturer(@PathVariable long id){
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer with id:" + id + "does not exist"));
        manufacturerRepository.delete(manufacturer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
