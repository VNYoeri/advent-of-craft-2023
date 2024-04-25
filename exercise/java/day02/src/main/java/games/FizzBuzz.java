package games;

import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class FizzBuzz {
    private FizzBuzz() {
    }

    public static String convert(Integer input) throws OutOfRangeException {
        return new Input(input).validateRange().convert();
    }

    private record Input(Integer input) {

        Input validateRange() throws OutOfRangeException {
            if (notWithinRange()) {
                throw new OutOfRangeException();
            }
            return this;
        }

        private boolean notWithinRange() {
            return inputIsLessThanOne() || inputIsMoreThanHundred();
        }

        private boolean inputIsMoreThanHundred() {
            return this.input > 100;
        }

        private boolean inputIsLessThanOne() {
            return this.input < 1;
        }

        public String convert() {
            return Stream.of(new Division.ByThree(this.input), new Division.ByFive(this.input))
                    .map(Division::word)
                    .filter(not(String::isBlank))
                    .reduce(String::concat)
                    .orElse(this.input.toString());
        }

    }

    private sealed interface Division {
        String word();

        record ByThree(Integer input) implements Division {

            @Override
            public String word() {
                return this.input % 3 == 0 ? "Fizz" : "";
            }
        }

        record ByFive(Integer input) implements Division {

            @Override
            public String word() {
                return this.input % 5 == 0 ? "Buzz" : "";
            }
        }
    }


}
