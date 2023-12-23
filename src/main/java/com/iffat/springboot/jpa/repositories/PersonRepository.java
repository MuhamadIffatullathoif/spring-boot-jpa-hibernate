package com.iffat.springboot.jpa.repositories;

import com.iffat.springboot.jpa.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
