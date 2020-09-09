import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void testTaskCreation() {
        try {
            assertEquals(
                    new AddCommand(TaskType.D, "lorem ipsum",
                            LocalDate.of(2020, 12, 12), LocalTime.of(23, 59)),
                    Parser.parse("deadline lorem ipsum /by 12-12-20 23:59"));
        } catch (MissingDelimiterException | MissingDateTimeException | InvalidCommandException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void parseInput_invalidCommand_exceptionThrown() {
        try {
            assertEquals(
                    new AddCommand(TaskType.D, "lorem ipsum",
                            LocalDate.of(2020, 12, 12), LocalTime.of(23, 59)),
                    Parser.parse("dadlines"));
            fail();
        } catch (MissingDelimiterException | MissingDateTimeException e) {
            e.printStackTrace();
            fail();
        } catch (InvalidCommandException e) {
            System.out.println("pass");
        }
    }
}