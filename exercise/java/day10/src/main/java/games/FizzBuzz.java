package games;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import static java.util.function.Predicate.not;

public class FizzBuzz {
    public static final int MIN = 0;
    public static final int MAX = 100;


    private FizzBuzz() {
    }

    public static String convert(Integer input) throws OutOfRangeException {
        return Optional.ofNullable(input)
                .filter(not(FizzBuzz::isOutOfRange))
                .map(FizzBuzz::convertSafely)
                .orElseThrow(OutOfRangeException::new);
    }

    private static String convertSafely(Integer input) {
        return Arrays.stream(FizzMapping.values())
                .sorted(Comparator.reverseOrder())
                .filter(fizz -> fizz.canDivide(input))
                .map(Enum::toString)
                .findFirst()
                .orElse(input.toString());
    }


    private static boolean isOutOfRange(Integer input) {
        return input <= MIN || input > MAX;
    }


    enum FizzMapping {
        FIZZ(3, "Fizz"),
        BUZZ(5, "Buzz"),
        FIZZBUZZ(15, "FizzBuzz");

        private final int divider;
        private final String name;

        FizzMapping(int divider, String name) {
            this.divider = divider;
            this.name = name;
        }


        boolean canDivide(Integer input) {
            return input % this.divider == 0;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
