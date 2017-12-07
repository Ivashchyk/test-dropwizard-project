package com.ivashchyk.dao;

import java.util.UUID;

import javax.inject.Inject;

import com.ivashchyk.api.Person;
import com.ivashchyk.dao.adapter.DaoAdapter;
import com.ivashchyk.dao.mapper.MapperDao;

public class PersonDaoImpl extends MapperDao<PersonMapper> implements PersonDao {

    @Inject
    public PersonDaoImpl(DaoAdapter<PersonMapper> daoAdapter) {
        super(daoAdapter);
    }

    @Override
    public Person insert(Person person) {
        return getMapper().insert(person);
    }

    @Override
    public Person get(UUID id) {
        return getMapper().get(id);
    }

    @Override
    public Person update(Person person) {
        return getMapper().update(person);
    }

    @Override
    public Person delete(Person person) {
        return getMapper().delete(person);
    }
}
