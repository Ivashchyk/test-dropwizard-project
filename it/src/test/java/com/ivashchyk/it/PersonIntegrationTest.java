package com.ivashchyk.it;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.ivashchyk.api.Person;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com.ivashchyk.api.PersonApi;
import com.ivashchyk.client.PersonClient;
import com.ivashchyk.web.resources.com.ivashchyk.web.app.TestDropwizardApplication;
import com.ivashchyk.web.resources.com.ivashchyk.web.app.TestDropwizardConfiguration;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PersonIntegrationTest {

    @ClassRule
    public static final DropwizardAppRule<TestDropwizardConfiguration> APP_RULE =
        new DropwizardAppRule<>(TestDropwizardApplication.class, ResourceHelpers.resourceFilePath("config-dev.yaml"));

    private static String uri;

    private static PersonApi personApi;

    private static Client simpleClient = APP_RULE.client();

    @BeforeClass
    public static void setUpClass() {
        uri = String.format("http://localhost:%d", APP_RULE.getLocalPort());
        personApi = new PersonClient(APP_RULE.client(), uri);
    }

    @Test
    public void testCreate() {
        Person inputPerson = new Person(UUID.randomUUID(), "test_person", 5);
        Person personViaSimpleClient = simpleClient.target(uri + "/person/create").request()
            .post(Entity.entity(inputPerson, MediaType.APPLICATION_JSON), Person.class);
        Person personViaApiClient = personApi.create(inputPerson);
        assertThat(personViaSimpleClient, is(personViaApiClient));
        assertThat(personViaApiClient.getName(), containsString("_inserted"));
    }

}
