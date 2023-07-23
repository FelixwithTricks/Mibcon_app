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
    /**
     * The ManufacturerRepository used to interact with the underlying database for Manufacturer entities.
     */
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    /**
     * Get all manufacturers from the database.
     *
     * @return A list of all Manufacturer entities available in the database.
     */
    @GetMapping
    public List<Manufacturer> getAllManufacturers(){
        return manufacturerRepository.findAll();
    }

    /**
     * Create a new manufacturer and save it to the database.
     *
     * @param manufacturer The Manufacturer object to be created and saved.
     * @return The newly created Manufacturer entity with its ID assigned.
     */
    @PostMapping
    public Manufacturer createManufacturer(@RequestBody Manufacturer manufacturer){
        return manufacturerRepository.save(manufacturer);
    }

    /**
     * Retrieve a manufacturer from the database by its ID.
     *
     * @param id The ID of the manufacturer to be retrieved.
     * @return ResponseEntity containing the Manufacturer entity if found, or 404 Not Found if the manufacturer does not exist.
     * @throws ResourceNotFoundException if the manufacturer with the specified ID does not exist.
     */
    @GetMapping("{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable long id){
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer with id:" + id + "does not exist"));
         return ResponseEntity.ok(manufacturer);
    }

    /**
     * Update an existing manufacturer's details in the database.
     *
     * @param id            The ID of the manufacturer to be updated.
     * @param manufacturerProperties The Manufacturer object containing the updated properties.
     * @return ResponseEntity containing the updated Manufacturer entity if found, or 404 Not Found if the manufacturer does not exist.
     * @throws ResourceNotFoundException if the manufacturer with the specified ID does not exist.
     */
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

    /**
     * Delete a manufacturer from the database by its ID.
     *
     * @param id The ID of the manufacturer to be deleted.
     * @return ResponseEntity with status NO_CONTENT if the manufacturer is successfully deleted.
     * @throws ResourceNotFoundException if the manufacturer with the specified ID does not exist.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteManufacturer(@PathVariable long id){
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer with id:" + id + "does not exist"));
        manufacturerRepository.delete(manufacturer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
