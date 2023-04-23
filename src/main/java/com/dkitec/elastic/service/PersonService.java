package com.dkitec.elastic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkitec.elastic.entity.Person;
import com.dkitec.elastic.repository.PersonRepository;

@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

  public Person insertPerson(Person person) {
    return personRepository.save(person);
  }
  
  public List<Person> findPersonsByName(String name) {
      return personRepository.findByName(name);
  }
}
