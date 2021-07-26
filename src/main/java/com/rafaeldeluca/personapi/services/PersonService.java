package com.rafaeldeluca.personapi.services;

import com.rafaeldeluca.personapi.entities.Person;
import com.rafaeldeluca.personapi.repositories.PersonRepository;
import com.rafaeldeluca.personapi.dto.request.PersonDTO;
import com.rafaeldeluca.personapi.dto.response.SucessMessageDTO;
import com.rafaeldeluca.personapi.dto.mapper.PersonMapper;
import com.rafaeldeluca.personapi.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonService {

    private PersonRepository personRepository;

    private  PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public SucessMessageDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);

        return createMessageResponse(savedPerson.getId(),"Create person with this ID: ");

    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());

    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        return personMapper.toDTO(person);

    }

    public void delete(Long id) throws PersonNotFoundException {

        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public Person verifyIfExists(Long id) throws PersonNotFoundException {

        return personRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException(id));

    }

    public SucessMessageDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {

        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatePerson = personRepository.save(personToUpdate);

        return createMessageResponse(updatePerson.getId(),"Update person with this ID: ");




        }

        private SucessMessageDTO createMessageResponse(Long id, String string) {
            return SucessMessageDTO
            .builder()
            .message(string + id)
            .build();
        }


    }
