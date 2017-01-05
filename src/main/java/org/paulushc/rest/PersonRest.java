package org.paulushc.rest;

import org.paulushc.models.Person;
import org.paulushc.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/person")
public class PersonRest {

    @Autowired private PersonRepository repository;

    @RequestMapping(value = "/all")
    public Iterable<Person> getAll(){
        return repository.findAll();
    }
}
