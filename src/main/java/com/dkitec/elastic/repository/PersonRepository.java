package com.dkitec.elastic.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.dkitec.elastic.entity.Person;

public interface PersonRepository extends ElasticsearchRepository<Person, String> {
	List<Person> findByName(String name);
}