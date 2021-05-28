package com.craftmanship;

import java.util.function.Predicate;

public class Validator {
    private final String value;
    private final String message;
    private final Predicate<String> validator;

    public Validator(String value, String message, Predicate<String> validator) {
        this.value = value;
        this.message = message;
        this.validator = validator;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid() {
        return validator.test(value);
    }
}
