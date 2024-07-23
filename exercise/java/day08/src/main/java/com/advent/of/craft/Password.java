package com.advent.of.craft;

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

    public boolean containsAtLeastOneApprovedSpecialCharacter() {
        char[] allowedSpecials = { '.', '*', '#', '@', '$', '%', '&' };

        for (char allowedSpecial : allowedSpecials) {
            if (this.input.contains(String.valueOf(allowedSpecial))) {
                return true;
            }
        }
        return false;
    }
}
