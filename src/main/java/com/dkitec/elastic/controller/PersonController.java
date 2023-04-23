package com.dkitec.elastic.controller;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dkitec.elastic.entity.Person;
import com.dkitec.elastic.service.PersonHttpService;
import com.dkitec.elastic.service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {

  @Autowired
  private PersonService personService;
  
  @Autowired
  private PersonHttpService personHttpService;

  @PostMapping("/persons")
  public Person insertPerson(@RequestBody Person person) {
    return personService.insertPerson(person);
  }
  
  @GetMapping("/persons")
  public List<Person> findPersonsByName(@RequestParam("name") String name) {
      return personService.findPersonsByName(name);
  }
  
  @PostMapping("/persons/http")
  public IndexResponse insertHttpPerson(@RequestBody Person person) throws IOException {
      return personHttpService.insertHttpPerson(person);
  }
  
  @GetMapping("/persons/http")
  public SearchResponse findHttpPersonByName(@RequestParam("name") String name) throws IOException {
      return personHttpService.findPersonByName(name);
  }
  
}
