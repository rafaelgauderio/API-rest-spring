package com.rafaeldeluca.personapi.repositories;

import com.rafaeldeluca.personapi.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

