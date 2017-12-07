package com.ivashchyk.api;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Person {

    @NotNull
    private UUID id;

    @NotNull
    private String name;

    @Min(0)
    private int age;

}


