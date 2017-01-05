package org.paulushc.rest;

import org.paulushc.models.Person;
import org.paulushc.repository.PersonRepository;
import org.paulushc.utils.EntityManagerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/multiperson")
public class MultiPersonRest {

    @Autowired private PersonRepository repository;
    @Autowired private HttpServletRequest context;
    @Autowired private EntityManagerUtils emUtils;

    @RequestMapping(value = "/all")
    public Iterable<Person> getAll(){
        setRepository( context.getRequestURL().toString() );
        return repository.findAll();
    }

    @RequestMapping(value = "/single/{id}")
    public Person getSingle(@PathVariable("id") Long id){
        setRepository( context.getRequestURL().toString() );
        return repository.findOne(id);
    }

    private void setRepository(String url){
        repository = emUtils.getJpaFactory(url).getRepository(PersonRepository.class);
    }

}

