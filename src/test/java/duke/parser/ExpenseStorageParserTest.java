package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import duke.exception.DukeParseException;
import duke.expense.Expense;
import duke.expense.Payable;
import duke.expense.Receivable;

public class ExpenseStorageParserTest {
    private static final ExpenseStorageParser EXPENSE_STORAGE_PARSER = new ExpenseStorageParser();

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
    public void convertStorageToExpense_correctSymbol_success(
            Expense actual, String storage) throws DukeParseException {
        Expense converted = EXPENSE_STORAGE_PARSER.parseStorageString(storage);
        assertEquals(converted, actual);
    }

    private static Stream<Arguments> getConvertStorageToExpenseExceptionArguments() {
        return Stream.of(
                Arguments.of("X;wrong symbol;$1.00;30 May 2020"),
                Arguments.of("P;no money;no money;30 May 2020"),
                Arguments.of("R;no date;$30.00"),
                Arguments.of("R;30 May 2020;$1.00")
        );
    }

    @ParameterizedTest
    @MethodSource("getConvertStorageToExpenseExceptionArguments")
    public void convertStorageToExpense_wrongSymbol_exceptionThrown(String storageString) {
        try {
            EXPENSE_STORAGE_PARSER.parseStorageString(storageString);
        } catch (DukeParseException exception) {
            assertNotEquals(exception.getMessage(), "");
        }
    }
}
