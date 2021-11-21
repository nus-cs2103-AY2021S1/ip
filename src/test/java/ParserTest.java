import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void testTaskCreation() {
        try {
            Parser parser = new Parser(new HashMap<>());
            assertEquals(
                    new AddTaskCommand(TaskType.D, "lorem ipsum",
                            LocalDate.of(2020, 12, 12), LocalTime.of(23, 59)),
                    parser.parse("deadline lorem ipsum /by 12-12-20 23:59"));
        } catch (MissingDelimiterException | MissingDateTimeException | InvalidCommandException | AliasNotAllowedException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void parseInput_invalidCommand_exceptionThrown() {
        try {
            Parser parser = new Parser(new HashMap<>());
            assertEquals(
                    new AddTaskCommand(TaskType.D, "lorem ipsum",
                            LocalDate.of(2020, 12, 12), LocalTime.of(23, 59)),
                    parser.parse("dadlines"));
            fail();
        } catch (MissingDelimiterException | MissingDateTimeException | AliasNotAllowedException e) {
            e.printStackTrace();
            fail();
        } catch (InvalidCommandException e) {
            System.out.println("pass");
        }
    }
}