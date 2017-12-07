package com.ivashchyk.api;

import java.util.UUID;

public interface PersonApi {

    Person create(Person person);

    Person read(UUID id);

    Person update(Person person);

    Person delete(Person person);

}
