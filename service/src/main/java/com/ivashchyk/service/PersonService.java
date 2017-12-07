package com.ivashchyk.service;

import java.util.UUID;

import javax.inject.Inject;

import com.ivashchyk.api.Person;
import com.ivashchyk.api.PersonApi;
import com.ivashchyk.dao.PersonDao;

public class PersonService implements PersonApi {

    private final PersonDao personDao;

    @Inject
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person create(Person person) {
        return personDao.insert(person);
    }

    @Override
    public Person read(UUID id) {
        return personDao.get(id);
    }

    @Override
    public Person update(Person person) {
        return personDao.update(person);
    }

    @Override
    public Person delete(Person person) {
        return personDao.delete(person);
    }
}
