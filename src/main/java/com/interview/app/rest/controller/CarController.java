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
    public int number = 1;
    @Autowired
    private CarRepository carRepository;
    //read all
    @GetMapping
    public List<Car> GetAllCars(){
        return carRepository.findAll();

    }

    //post
    @PostMapping
    public Car CreateCar(@RequestBody Car car){
        return carRepository.save(car);
    }

    //read by ID
    @GetMapping("{id}")
    public ResponseEntity<Car> GetCarById(@PathVariable long id){
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id:" + id + "does not exist"));
        return ResponseEntity.ok(car);
    }
    //update
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

    //delete
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable long id){
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id:" + id + "does not exist"));
        carRepository.delete(car);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
