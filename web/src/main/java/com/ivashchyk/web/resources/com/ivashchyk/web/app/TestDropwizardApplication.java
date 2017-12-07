package com.ivashchyk.web.resources.com.ivashchyk.web.app;

import java.util.UUID;

import javax.inject.Singleton;

import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.ivashchyk.api.Person;
import com.ivashchyk.api.PersonApi;
import com.ivashchyk.dao.PersonDao;
import com.ivashchyk.dao.PersonDaoImpl;
import com.ivashchyk.dao.PersonMapper;
import com.ivashchyk.dao.adapter.DaoAdapter;
import com.ivashchyk.service.PersonService;
import com.ivashchyk.web.resources.PersonResource;

import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;

public class TestDropwizardApplication extends Application<TestDropwizardConfiguration> {

    @Override
    public void run(TestDropwizardConfiguration configuration, Environment environment) throws Exception {
        registerResources(environment.jersey());
        registerDaoComponents(environment.jersey());
        registerServiceComponents(environment.jersey());
    }

    public static void main(String[] args) throws Exception {
        new TestDropwizardApplication()
            .run("server", "C:\\dev\\repos_other\\test-dropwizard-project\\web\\src\\main\\resources\\config-dev.yaml");
    }

    private void registerResources(JerseyEnvironment jerseyEnvironment) {
        jerseyEnvironment.register(PersonResource.class);
    }

    private void registerDaoComponents(JerseyEnvironment jerseyEnvironment) {
        DaoAdapter<PersonMapper> personDaoAdapter = () -> new PersonMapper() {

            @Override
            public Person insert(Person person) {
                if (person.getId() == null) {
                    person.setId(UUID.randomUUID());
                }
                person.setName(person.getName() + "_inserted");
                return person;
            }

            @Override
            public Person get(UUID id) {
                return new Person(id, "Person", 5);
            }

            @Override
            public Person update(Person person) {
                return new Person(person.getId(), person.getName() + "_updated", person.getAge() + 1);
            }

            @Override
            public Person delete(Person person) {
                return new Person(person.getId(), person.getName() + "_deleted", 0);
            }
        };
        jerseyEnvironment.register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(personDaoAdapter).to(new TypeLiteral<DaoAdapter<PersonMapper>>() { });
                bind(PersonDaoImpl.class).to(PersonDao.class).in(Singleton.class);
            }
        });
    }

    private void registerServiceComponents(JerseyEnvironment jerseyEnvironment) {
        jerseyEnvironment.register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(PersonService.class).to(PersonService.class).to(PersonApi.class).in(Singleton.class);
            }
        });
    }

}
