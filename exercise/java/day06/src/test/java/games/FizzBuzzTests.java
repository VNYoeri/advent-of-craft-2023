package games;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static games.FizzBuzzTests.TestData.buzz;
import static games.FizzBuzzTests.TestData.fizz;
import static games.FizzBuzzTests.TestData.fizzBuzz;
import static games.FizzBuzzTests.TestData.sameAsInput;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FizzBuzzTests {

    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 101 })
    void throwsExceptionWhenInputIsInvalid(int number) {
        assertThatThrownBy(() -> FizzBuzz.convert(number))
                .isInstanceOf(OutOfRangeException.class);
    }


    @ParameterizedTest
    @MethodSource
    void returnsExpectedOutcome(TestData underTest) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(underTest.input()))
                .isEqualTo(underTest.expectedOutput());
    }

    public static Stream<TestData> returnsExpectedOutcome() {
        return Stream.of(
                sameAsInput(1),
                sameAsInput(67),
                sameAsInput(82),
                fizz(3),
                fizz(33),
                fizz(66),
                fizz(99),
                buzz(5),
                buzz(50),
                buzz(55),
                buzz(85),
                fizzBuzz(15),
                fizzBuzz(30),
                fizzBuzz(45)
        );
    }

    record TestData(int input, String expectedOutput) {

        static TestData sameAsInput(int input) {
            return new TestData(input, String.valueOf(input));
        }

        static TestData fizz(int input) {
            return new TestData(input, "Fizz");
        }
        static TestData buzz(int input) {
            return new TestData(input, "Buzz");
        }
        static TestData fizzBuzz(int input) {
            return new TestData(input, "FizzBuzz");
        }
    }
}