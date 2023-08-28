package com.example.appwebsenai.controller;

import com.example.appwebsenai.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer > {
}
