package com.advent.of.craft;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordTest {

    @Nested
    class LengthCheck {
        @ParameterizedTest
        @ValueSource(strings = { "12345678", "123456789" })
        void passwordIsAtLeastEightLong(String input) {
            var password = new Password(input);
            assertThat(password.hasLengthOfEightOrMore()).isTrue();
        }

        @Test
        void passwordLessThenEightLong() {
            var password = new Password("1234567");
            assertThat(password.hasLengthOfEightOrMore()).isFalse();
        }
    }

    @Nested
    class UpperCaseCheck {
        @ParameterizedTest
        @ValueSource(strings = { "Fasdfsadf", "aDSFASDFASDF", "FGHJKL" })
        void passwordHasAtLeastOneUpperCaseCharacter(String input) {
            var password = new Password(input);
            assertThat(password.containsAtLeastOneUppercaseCharacter()).isTrue();
        }

        @Test
        void passwordWithoutOneUpperCaseCharacter() {
            var password = new Password("asdfghjkl");
            assertThat(password.containsAtLeastOneUppercaseCharacter()).isFalse();
        }
    }

    @Nested
    class LowerCaseCheck {
        @ParameterizedTest
        @ValueSource(strings = { "Fasdfsadf", "aDSFASDFASDF", "asdfasdf" })
        void passwordHasAtLeastOneLowerCaseCharacter(String input) {
            var password = new Password(input);
            assertThat(password.containsAtLeastOneLowercaseCharacter()).isTrue();
        }

        @Test
        void passwordWithoutOneLowerCaseCharacter() {
            var password = new Password("FGHJKL");
            assertThat(password.containsAtLeastOneLowercaseCharacter()).isFalse();
        }
    }

    @Nested
    class NumericCharacterCheck {
        @ParameterizedTest
        @ValueSource(strings = { "1digit", "2digits2", "3dig3its3" })
        void passwordHasAtLeastOneDigit(String input) {
            var password = new Password(input);
            assertThat(password.containsAtLeastOneDigit()).isTrue();
        }

        @Test
        void passwordWithoutADigit() {
            var password = new Password("FGHJKLfsdaf");
            assertThat(password.containsAtLeastOneDigit()).isFalse();
        }
    }

    @Nested
    class SpecialCharacterCheck {
        @ParameterizedTest
        @ValueSource(strings = { ".*#@$%&", "blub#", "&sdf$" })
        void passwordHasAtLeastAnAllowedSpecialCharacter(String input) {
            var password = new Password(input);
            assertThat(password.containsAllowedSpecialCharacter()).isTrue();
        }

        @Test
        void passwordWithoutASpecialCharacter() {
            var password = new Password("FGHJKLfsdaf");
            assertThat(password.containsAllowedSpecialCharacter()).isFalse();
        }

        @Test
        void passwordWithASpecialCharacterThatIsNotAllowed() {
            var password = new Password("~");
            assertThat(password.containsAllowedSpecialCharacter()).isFalse();
        }
    }

    @Nested
    class InvalidCharacterCheck {
        @Test
        void  passwordDoesNotContainUnsupportedCharacter() {
            var password = new Password("Blub@21#$%&*");
            assertThat(password.containsNoUnsupportedCharacter()).isTrue();
        }
        @Test
        void  passwordContainsUnsupportedCharacter() {
            var password = new Password("bl!^ub&1~!");
            assertThat(password.containsNoUnsupportedCharacter()).isFalse();
        }
    }

}
