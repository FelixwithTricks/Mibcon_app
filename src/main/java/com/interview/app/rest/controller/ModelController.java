package com.interview.app.rest.controller;

import com.interview.app.rest.exception.ResourceNotFoundException;
import com.interview.app.rest.model.Model;
import com.interview.app.rest.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/models")
public class ModelController {
    /**
     * The ModelRepository used to interact with the underlying database for Model entities.
     */
    @Autowired
    private ModelRepository modelRepository;

    /**
     * Get all models from the database.
     *
     * @return A list of all Model entities available in the database.
     */
    @GetMapping
    public List<Model> GetAllModels(){
        return modelRepository.findAll();
    }

    /**
     * Create a new model and save it to the database.
     *
     * @param model The Model object to be created and saved.
     * @return The newly created Model entity with its ID assigned.
     */
    @PostMapping
    public Model CreateModel(@RequestBody Model model){
        return modelRepository.save(model);
    }

    /**
     * Retrieve a model from the database by its ID.
     *
     * @param id The ID of the model to be retrieved.
     * @return ResponseEntity containing the Model entity if found, or 404 Not Found if the model does not exist.
     * @throws ResourceNotFoundException if the model with the specified ID does not exist.
     */
    @GetMapping("{id}")
    public ResponseEntity<Model> GetModelById(@PathVariable long id){
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model with id:" + id + "does not exist"));
        return ResponseEntity.ok(model);
    }
    /**
     * Update an existing model's details in the database.
     *
     * @param id            The ID of the model to be updated.
     * @param modelProperties The Model object containing the updated properties.
     * @return ResponseEntity containing the updated Model entity if found, or 404 Not Found if the model does not exist.
     * @throws ResourceNotFoundException if the model with the specified ID does not exist.
     */
    @PutMapping("{id}")
    public ResponseEntity<Model> updateModel(@PathVariable long id, @RequestBody Model modelProperties) {
        Model updateModel = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model with id:" + id + "does not exist"));
        updateModel.setManufacturerId(modelProperties.getManufacturerId());
        updateModel.setName(modelProperties.getName());
        updateModel.setCategory(modelProperties.getCategory());
        updateModel.setPrice(modelProperties.getPrice());
        updateModel.setDateOfMarketIntroduction(modelProperties.getDateOfMarketIntroduction());
        updateModel.setActiveProduction(modelProperties.isActiveProduction());

        modelRepository.save(updateModel);

        return ResponseEntity.ok(updateModel);
    }

    /**
     * Delete a model from the database by its ID.
     *
     * @param id The ID of the model to be deleted.
     * @return ResponseEntity with status NO_CONTENT if the model is successfully deleted.
     * @throws ResourceNotFoundException if the model with the specified ID does not exist.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteModel(@PathVariable long id){
        Model model = modelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Model with id:" + id + "does not exist"));
        modelRepository.delete(model);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
