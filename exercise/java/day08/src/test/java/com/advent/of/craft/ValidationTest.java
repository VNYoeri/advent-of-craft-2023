package com.advent.of.craft;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidationTest {

//    Contains at least 8 characters
//    Contains at least one capital letter
//    Contains at least one lowercase letter
//    Contains at least a number
//    Contains at least a special character in this list . * # @ $ % &.
//    Any other characters are not authorized.
    @Test
    void validPasswordsShouldPassValidation() {
        var password = "12345678lF.#";
        Validator validator = new Validator(new Password(password));

        assertThat(validator.isValid()).isTrue();
    }
}
