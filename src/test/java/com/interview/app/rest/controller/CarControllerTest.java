package com.interview.app.rest.controller;

import com.interview.app.rest.exception.ResourceNotFoundException;
import com.interview.app.rest.model.Car;
import com.interview.app.rest.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CarControllerTest {

/*
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCars() {
        // Mocking the carRepository.findAll() method
        when(carRepository.findAll()).thenReturn(Arrays.asList(
                new Car(1L, 1L, "Red", "200", "8.5", new Date(System.currentTimeMillis()), true),
                new Car(2, "Model 2", "Blue", 180, 7.5, "2023-07-23", true)
        ));

        List<Car> cars = carController.GetAllCars();

        assertEquals(2, cars.size());
    }

    @Test
    void testCreateCar() {
        Car car = new Car(1L, "Model 1", "Red", 200, 8.5, "2023-07-23", true);

        // Mocking the carRepository.save() method
        when(carRepository.save(any())).thenReturn(car);

        Car createdCar = carController.CreateCar(car);

        assertEquals(car.getId(), createdCar.getId());
        // Add more assertions for other properties if needed
    }

    @Test
    void testGetCarById() {
        long id = 1L;
        Car car = new Car(id, "Model 1", "Red", 200, 8.5, "2023-07-23", true);

        // Mocking the carRepository.findById() method
        when(carRepository.findById(id)).thenReturn(Optional.of(car));

        ResponseEntity<Car> response = carController.GetCarById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car.getId(), response.getBody().getId());
    }

    @Test
    void testGetCarByIdNotFound() {
        long id = 1L;

        // Mocking the carRepository.findById() method to return an empty optional
        when(carRepository.findById(id)).thenReturn(Optional.empty());

        try {
            carController.GetCarById(id);
        } catch (ResourceNotFoundException e) {
            assertEquals("Car with id:" + id + " does not exist", e.getMessage());
        }
    }

    @Test
    void testUpdateCar() {
        long id = 1L;
        Car existingCar = new Car(id, "Model 1", "Red", 200, 8.5, "2023-07-23", true);
        Car updatedCarProperties = new Car(id, "Updated Model", "Yellow", 220, 9.0, "2023-07-23", false);

        // Mocking the carRepository.findById() method
        when(carRepository.findById(id)).thenReturn(Optional.of(existingCar));
        // Mocking the carRepository.save() method
        when(carRepository.save(any())).thenReturn(updatedCarProperties);

        ResponseEntity<Car> response = carController.updateCar(id, updatedCarProperties);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCarProperties.getModelId(), response.getBody().getModelId());
        assertEquals(updatedCarProperties.getColor(), response.getBody().getColor());
        // Add more assertions for other properties if needed
    }

    @Test
    void testDeleteCar() {
        long id = 1L;
        Car car = new Car(id, "Model 1", "Red", 200, 8.5, "2023-07-23", true);

        // Mocking the carRepository.findById() method
        when(carRepository.findById(id)).thenReturn(Optional.of(car));

        ResponseEntity<HttpStatus> response = carController.deleteCar(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(carRepository, times(1)).delete(car);
    }

    @Test
    void testDeleteCarNotFound() {
        long id = 1L;

        // Mocking the carRepository.findById() method to return an empty optional
        when(carRepository.findById(id)).thenReturn(Optional.empty());

        try {
            carController.deleteCar(id);
        } catch (ResourceNotFoundException e) {
            assertEquals("Car with id:" + id + " does not exist", e.getMessage());
       }
    }*/
}
