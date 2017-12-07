package com.ivashchyk.web.resources;

import java.util.UUID;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ivashchyk.api.Person;
import com.ivashchyk.api.PersonApi;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final PersonApi personApi;

    @Inject
    public PersonResource(PersonApi personApi) {
        this.personApi = personApi;
    }

    @POST
    @Path("/create")
    public Person create(@Valid Person person) {
        return personApi.create(person);
    }

    @GET
    @Path("/get/{id}")
    public Person get(@PathParam("id") @Valid UUID uuid) {
        return personApi.read(uuid);
    }

    @PUT
    @Path("/update")
    public Person update(Person person) {
        return personApi.update(person);
    }

    @DELETE
    @Path("/delete")
    public Person delete(Person person) {
        return personApi.delete(person);
    }

}
