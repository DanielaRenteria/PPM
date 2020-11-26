package com.init.products.rest;


import java.util.List;
import java.util.Optional;
import com.init.products.dao.MembersDAO;
import com.init.products.entitys.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/members")
public class MembersREST {

    @Autowired
    private MembersDAO membersDAO;

    @GetMapping
    public ResponseEntity<List<Members>> getMembers(){
        List<Members> members = membersDAO.findAll();
        return ResponseEntity.ok(members);
    }

    @RequestMapping(value="{membersId}")// /members/{membersId} -> /members/1
    public ResponseEntity<Members> getMembersById(@PathVariable("membersId") Long membersId){
        Optional<Members> optionalMembers= membersDAO.findById(membersId);
        if(optionalMembers.isPresent()){
            return ResponseEntity.ok(optionalMembers.get());
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    //Insertar a la base de datos
    @PostMapping // /members (POST)
    public ResponseEntity<Members> createMembers(@RequestBody Members members){
        Members newMembers = membersDAO.save(members);
        return ResponseEntity.ok(newMembers);
    }

    @DeleteMapping(value="{membersId}") // /members(DELETE)
    public ResponseEntity<Void> deleteMembers(@PathVariable("membersId") Long membersId){
        membersDAO.deleteById(membersId);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Members> updateMembers(@RequestBody Members members){
        Optional<Members> optionalMembers= membersDAO.findById(members.getId());
        if(optionalMembers.isPresent()) {
            Members updateMembers = optionalMembers.get();
            updateMembers.setName(members.getName());
            updateMembers.setLastName(members.getLastName());
            updateMembers.setRole(members.getRole());
            updateMembers.setActive(members.getActive());
            membersDAO.save(updateMembers);
            return ResponseEntity.ok(updateMembers);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
