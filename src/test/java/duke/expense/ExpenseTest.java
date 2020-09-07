package duke.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ExpenseTest {
    private static Stream<Arguments> getArguments() {
        LocalDateTime dateTimeOne = LocalDateTime.of(2010, 5, 30, 14, 20);
        LocalDateTime dateTimeTwo = LocalDateTime.of(2020, 8, 8, 8, 8);
        return Stream.of(
                Arguments.of(new Payable("lunch", 5.55, dateTimeOne),
                        "P;lunch;$5.55;May 30 2010"),
                Arguments.of(new Payable("ezlink card top up", 20, dateTimeTwo),
                        "P;ezlink card top up;$20.00;Aug 08 2020"),
                Arguments.of(new Receivable("from Duke", 1, dateTimeOne),
                        "R;from Duke;$1.00;May 30 2010"),
                Arguments.of(new Receivable("allowance", 1000.00, dateTimeTwo),
                        "R;allowance;$1000.00;Aug 08 2020")
        );
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    public void testConvertExpenseToStorage(Expense expense, String actual) {
        String converted = expense.convertToStorageString();
        assertEquals(converted, actual + "\n");

    }
}
