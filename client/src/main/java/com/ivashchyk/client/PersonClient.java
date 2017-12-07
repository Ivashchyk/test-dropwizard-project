package com.ivashchyk.client;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.ivashchyk.api.Person;
import com.ivashchyk.api.PersonApi;

import lombok.NonNull;

public class PersonClient implements PersonApi {

    private final Client client;

    private final String uri;

    @Inject
    public PersonClient(@Named("personClient") @NonNull Client client, @Named("personResourceUri") @NonNull String uri) {
        this.client = client;
        this.uri = uri;
    }

    @Override
    public Person create(Person person) {
        return client.target(uri + "/person/create").request().post(Entity.entity(person, MediaType.APPLICATION_JSON), Person.class);
    }

    @Override
    public Person read(UUID id) {
        return client.target(uri + "/person/get/" + id).request().get(Person.class);
    }

    @Override
    public Person update(Person person) {
        return client.target(uri + "/person/update").request().put(Entity.entity(person, MediaType.APPLICATION_JSON), Person.class);
    }

    @Override
    public Person delete(Person person) {
        return client.target(uri + "/person/delete").request().delete(Person.class);
    }
}
