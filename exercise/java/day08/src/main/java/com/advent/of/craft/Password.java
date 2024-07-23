package com.advent.of.craft;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public record Password(String input) {
    public boolean hasLengthOfEightOrMore() {
        return this.input.length() >= 8;
    }

    public boolean containsAtLeastOneUppercaseCharacter() {
        return this.input.chars().anyMatch(Character::isUpperCase);
    }

    public boolean containsAtLeastOneLowercaseCharacter() {
        return this.input.chars().anyMatch(Character::isLowerCase);
    }

    public boolean containsAtLeastOneDigit() {
        return this.input.chars().anyMatch(Character::isDigit);
    }

    public boolean containsAllowedSpecialCharacter() {
        return this.input.chars().mapToObj(x -> (char) x)
                .anyMatch(this::isAllowed);
    }

    public boolean containsNoUnsupportedCharacter() {
        return this.input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !isAllowed(c))
                .filter(c -> !Character.isLetterOrDigit(c))
                .findFirst()
                .isEmpty();
    }

    private boolean isAllowed(Character character) {
        return Stream.of(ALLOWED.values())
                .map(ALLOWED::character)
                .anyMatch(character::equals);
    }

    enum ALLOWED {
        DOT('.'),
        ASTERISK('*'),
        HASHTAG('#'),
        AT('@'),
        DOLLAR('$'),
        PERCENT('%'),
        AMPERSAND('&');

        private final Character character;

        ALLOWED(Character character) {
            this.character = character;
        }

        Character character() {
            return this.character;
        }
    }
}
