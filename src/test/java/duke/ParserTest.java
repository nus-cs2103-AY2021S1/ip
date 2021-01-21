package duke;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_noDescription_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (Exception e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_noDate_exceptionThrown() {
        try {
            Parser.parse("deadline read book");
            fail();
        } catch (Exception e) {
            assertEquals("Deadline is missing a date.", e.getMessage());
        }
    }

    @Test
    public void parse_wrongDateFormat_exceptionThrown() {
        try {
            Parser.parse("deadline read book /by Monday");
            fail();
        } catch (Exception e) {
            assertEquals("Invalid date format", e.getMessage());
        }
    }



}
