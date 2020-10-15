package duke.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ReceivableTest {
    private static LocalDateTime getLocalDate() {
        return LocalDateTime.of(2020, 12, 12, 6, 0);
    }

    private static Stream<Arguments> getPayableArguments() {
        return Stream.of(
                Arguments.of(new Receivable("test", 0.10, getLocalDate()), 0.10),
                Arguments.of(new Receivable("test 2", 15.05, getLocalDate()), 15.05),
                Arguments.of(new Receivable("test 3", 100, getLocalDate()), 100.0)
        );
    }

    @ParameterizedTest
    @MethodSource("getPayableArguments")
    public void testIsPayable(Receivable receivable) {
        assertFalse(receivable.isPayable());
    }

    @ParameterizedTest
    @MethodSource("getPayableArguments")
    public void testGetValue(Receivable receivable, double value) {
        assertEquals(receivable.getValue(), value);
    }
}
