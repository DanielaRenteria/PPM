package com.init.products.rest;

import java.util.List;
import java.util.Optional;


import com.init.products.dao.TechnologiesDAO;
import com.init.products.entitys.Technologies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/technologies")
public class TechnologiesREST {

    @Autowired
    private TechnologiesDAO technologiesDAO;

    @GetMapping
    public ResponseEntity<List<Technologies>> getTechnologies(){
        List<Technologies> technologies = technologiesDAO.findAll();
        return ResponseEntity.ok(technologies);
    }

    @RequestMapping(value="{technologiesId}")// /products/{productId} -> /products/1
    public ResponseEntity<Technologies> getTechnologiesById(@PathVariable("technologiesId") Long technologiesId){
        Optional<Technologies> optionalTechnologies= technologiesDAO.findById(technologiesId);
        if(optionalTechnologies.isPresent()){
            return ResponseEntity.ok(optionalTechnologies.get());
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    //Insertar a la base de datos
    @PostMapping // /products (POST)
    public ResponseEntity<Technologies> createTechnologies(@RequestBody Technologies technologies){
        Technologies newTechnologies = technologiesDAO.save(technologies);
        return ResponseEntity.ok(newTechnologies);
    }

    @DeleteMapping(value="{technologiesId}") // /technologies(DELETE)
    public ResponseEntity<Void> deleteTechnologies(@PathVariable("technologiesId") Long technologiesId){
        technologiesDAO.deleteById(technologiesId);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Technologies> updateTechnologies(@RequestBody Technologies technologies){
        Optional<Technologies> optionalTechnologies= technologiesDAO.findById(technologies.getId());
        if(optionalTechnologies.isPresent()) {
            Technologies updateTechnologies = optionalTechnologies.get();
            updateTechnologies.setName(technologies.getName());
            technologiesDAO.save(updateTechnologies);
            return ResponseEntity.ok(updateTechnologies);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value="hello", method=RequestMethod.GET)
    public String hello() {
        return "Hello Word";
    }
}