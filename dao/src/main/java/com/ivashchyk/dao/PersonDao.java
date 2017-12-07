package com.ivashchyk.dao;

import java.util.UUID;

import com.ivashchyk.api.Person;

public interface PersonDao {

    Person insert(Person person);

    Person get(UUID id);

    Person update(Person person);

    Person delete(Person person);

}
