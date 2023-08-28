package com.example.appwebsenai.view;

import com.example.appwebsenai.controller.Controller;
import com.example.appwebsenai.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class PersonView {

    @Autowired
    Controller controller;
    @GetMapping("/person")
    public Person findPerson(@PathParam("personId") Integer personId){
        return controller.findPerson(personId);
    }


    @DeleteMapping("/person")
    public String deletePerson(@PathParam("personId") Integer personId){
        controller.removePerson(personId);

        return "Pessoa com o ID " + personId + " foi deletada";
    }

    @PostMapping("/person")
    public Person addPerson(@PathParam("name") String name, @PathParam("sexo") String sexo){
        return controller.addPerson(name, sexo);
    }

    @PutMapping("/person")
    public Person updatePerson(@PathParam("personId") Integer personId, @PathParam("sexo") String sexo){
        return controller.editPerson(personId, sexo);
    }

    @GetMapping("/all")
    public List<Person> listAll(){
        return controller.listAll();
    }

    @GetMapping("/home")
    public String helloWorld(){
        return "Hello world";
    }
}