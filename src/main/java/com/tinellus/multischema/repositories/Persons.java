package com.tinellus.multischema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tinellus.multischema.entities.Person;

public interface Persons extends JpaRepository<Person, Long> {
	static Person named(String name) {
		Person person = new Person();
		person.setName(name);
		return person;
	}
}
