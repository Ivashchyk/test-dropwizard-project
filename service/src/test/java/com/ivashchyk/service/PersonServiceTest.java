package com.ivashchyk.service;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.ivashchyk.api.Person;
import com.ivashchyk.dao.PersonDao;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonServiceTest {

    private PersonService personService;

    private PersonDao personDao = mock(PersonDao.class);

    @Before
    public void setUp() {
        personService = new PersonService(personDao);
    }

    @Test
    public void testRead() {
        UUID uuid = UUID.randomUUID();
        Person expected = mock(Person.class);
        when(personDao.get(uuid)).thenReturn(expected);
        Person actual = personService.read(uuid);
        assertSame(expected, actual);
    }

}
