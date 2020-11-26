package com.init.products.rest;

import com.init.products.dao.MembersDAO;
import com.init.products.dao.TechnologiesForUserDAO;
import com.init.products.entitys.Members;
import com.init.products.entitys.TechnologiesForUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController(value = "/forUser")
public class TechnologiesForUserREST {

    @Autowired
    TechnologiesForUserDAO technologiesForUserDAO;

    @Autowired
    MembersDAO membersDAO;

    /*@GetMapping("/gg")
    public List < TechnologiesForUser > getTechnologiesForUser() {
        return technologiesForUserDAO.findAll();
    }

    @RequestMapping(value="{technologiesId}")// /products/{productId} -> /products/1
    public ResponseEntity<TechnologiesForUser> getTechnologiesForUserById(@PathVariable("technologiesId") Long technologiesId){
        Optional<TechnologiesForUser> optionalTechnologiesForUser= technologiesForUserDAO.findById(technologiesId);
        if(optionalTechnologiesForUser.isPresent()){
            return ResponseEntity.ok(optionalTechnologiesForUser.get());
        }else {
            return ResponseEntity.noContent().build();
        }
    }*/


    @PostMapping(path = "/forUsers/add")
    TechnologiesForUser createTechnologiesForUser(@RequestBody TechnologiesForUser technologiesForUser) {
        Members members = new Members();
        members.setName("");
        members.setLastName("");
        members.setRole("");
        technologiesForUser.setMembers(members);
        technologiesForUser.setMembers(members);
        return technologiesForUserDAO.save(technologiesForUser);
    }

    @PutMapping(path = "/forUser/{id}")
    Optional<TechnologiesForUser> updateTechnologiesForUser(@PathVariable Long id, @RequestBody TechnologiesForUser requestTechnologiesForUser){
        return technologiesForUserDAO.findById(id).map(technologiesForUser -> {
            technologiesForUser.setActive(requestTechnologiesForUser.getActive());
            technologiesForUser.setPercentage(requestTechnologiesForUser.getPercentage());

            return technologiesForUserDAO.save(technologiesForUser);
        });
    }

    @DeleteMapping(path = "/forUser/{id}")
    Optional<TechnologiesForUser> deleteTechnologiesForUser(@PathVariable Long id){
        return technologiesForUserDAO.findById(id).map(technologiesForUser -> {
            technologiesForUser.setActive(0);
            technologiesForUser.getMembers().setActive(0);

            return technologiesForUserDAO.save(technologiesForUser);
        });
    }

    @GetMapping(path = "/forUser")
    public List < TechnologiesForUser > getTechnologiesForUser() {
        return technologiesForUserDAO.findAll();
    }

    @RequestMapping(path = "/forUser/{id}")// /products/{productId} -> /products/1
    public ResponseEntity<TechnologiesForUser> getTechnologiesForUserById(@PathVariable("id") Long id){
        Optional<TechnologiesForUser> optionalTechnologiesForUser= technologiesForUserDAO.findById(id);
        if(optionalTechnologiesForUser.isPresent()){
            return ResponseEntity.ok(optionalTechnologiesForUser.get());
        }else {
            return ResponseEntity.noContent().build();
        }
    }


}
