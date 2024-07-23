package com.advent.of.craft;

public record Validator(Password password) {
    public boolean isValid() {
        return this.password.hasLengthOfEightOrMore()
                && this.password.containsAtLeastOneUppercaseCharacter()
                && this.password.containsAtLeastOneLowercaseCharacter()
                && this.password.containsAtLeastOneDigit()
                && this.password.containsAtLeastOneApprovedSpecialCharacter();
    }
}
