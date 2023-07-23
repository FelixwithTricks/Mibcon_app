package com.interview.app.rest.controller;

import com.interview.app.rest.exception.ResourceNotFoundException;
import com.interview.app.rest.model.Car;
import com.interview.app.rest.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/cars")
public class CarController {
    /**
     * The CarRepository used to interact with the underlying database for Car entities.
     */
    @Autowired
    private CarRepository carRepository;

    /**
     * Get all cars from the database.
     *
     * @return A list of all Car entities available in the database.
     */
    @GetMapping
    public List<Car> GetAllCars(){
        return carRepository.findAll();

    }

    /**
     * Create a new car and save it to the database.
     *
     * @param car The Car object to be created and saved.
     * @return The newly created Car entity with its ID assigned.
     */
    @PostMapping
    public Car CreateCar(@RequestBody Car car){
        return carRepository.save(car);
    }

    /**
     * Retrieve a car from the database by its ID.
     *
     * @param id The ID of the car to be retrieved.
     * @return ResponseEntity containing the Car entity if found, or 404 Not Found if the car does not exist.
     * @throws ResourceNotFoundException if the car with the specified ID does not exist.
     */
    @GetMapping("{id}")
    public ResponseEntity<Car> GetCarById(@PathVariable long id){
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id:" + id + "does not exist"));
        return ResponseEntity.ok(car);
    }
    /**
     * Update an existing car's details in the database.
     *
     * @param id The ID of the car to be updated.
     * @param carProperties The Car object containing the updated properties.
     * @return ResponseEntity containing the updated Car entity if found, or 404 Not Found if the car does not exist.
     * @throws ResourceNotFoundException if the car with the specified ID does not exist.
     */
    @PutMapping("{id}")
    public ResponseEntity<Car> updateCar(@PathVariable long id, @RequestBody Car carProperties) {
        Car updateCar = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id:" + id + "does not exist"));
        updateCar.setModelId(carProperties.getModelId());
        updateCar.setColor(carProperties.getColor());
        updateCar.setPerformance(carProperties.getPerformance());
        updateCar.setConsumption(carProperties.getConsumption());
        updateCar.setCreationDate(carProperties.getCreationDate());
        updateCar.setIsMobile(carProperties.getIsMobile());

        carRepository.save(updateCar);

        return ResponseEntity.ok(updateCar);
    }

    /**
     * Delete a car from the database by its ID.
     *
     * @param id The ID of the car to be deleted.
     * @return ResponseEntity with status NO_CONTENT if the car is successfully deleted.
     * @throws ResourceNotFoundException if the car with the specified ID does not exist.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable long id){
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id:" + id + "does not exist"));
        carRepository.delete(car);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
