package com.example.appwebsenai.controller;

import com.example.appwebsenai.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Controller {

    @Autowired
    private PersonRepository personRepository;
    private List<Person> persons = new ArrayList<>();
    private int id = 0;

    public Person findPerson(Integer id) {
        List<Person> persons = (List<Person>) personRepository.findAll();

        for (Person person : persons) {
            if (person.getId().equals(id)) {
                return person;
            }
        }

        return null;
    }

    public Person addPerson(String name, String sexo){
        Person person = new Person();
        person.setName(name);
        person.setSexo(sexo);
        id++;
        person.setId(id);
        personRepository.save(person);
        return person;
    }

    public void removePerson(Integer personId){
        Person person = findPerson(personId);
        personRepository.delete(person);
    }

    public Person editPerson(Integer personId, String sexo){
        Person person = findPerson(personId);
        person.setSexo(sexo);
        personRepository.save(person);
        return person;
    }

    public List<Person> listAll(){
        return (List<Person>)personRepository.findAll();
    }

}
