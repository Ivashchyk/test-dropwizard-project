package com.ivashchyk.dao;

import com.ivashchyk.api.Person;
import com.ivashchyk.dao.mapper.Mapper;

import java.util.UUID;

public interface PersonMapper extends Mapper {

    Person insert(Person person);

    Person get(UUID id);

    Person update(Person person);

    Person delete(Person person);

}
