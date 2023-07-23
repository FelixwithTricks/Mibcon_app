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
    @Autowired
    private ModelRepository modelRepository;
    //read all
    @GetMapping
    public List<Model> GetAllModels(){
        return modelRepository.findAll();
    }
    //post
    @PostMapping
    public Model CreateModel(@RequestBody Model model){
        return modelRepository.save(model);
    }
    //read by ID
    @GetMapping("{id}")
    public ResponseEntity<Model> GetModelById(@PathVariable long id){
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model with id:" + id + "does not exist"));
        return ResponseEntity.ok(model);
    }
    //update
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

    //delete
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteModel(@PathVariable long id){
        Model model = modelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Model with id:" + id + "does not exist"));
        modelRepository.delete(model);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
