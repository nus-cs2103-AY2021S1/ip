package duke;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void testDateParser() {
        assertEquals("Sep 09 2020 10:00AM", Parser.DateParser("09/09/2020 1000"));
    }

    @Test
    public void parse_incompleteDescription_exceptionThrown() {
        try {
            Parser.parse("todo"); //missing description
            fail();
        } catch (DukeException e) {
            assertEquals("The description of todo cannot be empty. Please re-enter", e.getMessage());
        }
    }

    @Test
    public void parse_wrongCommand_exceptionThrown() {
        try {
            Parser.parse("blahh"); //invalid input
            fail();
        } catch (DukeException e) {
            assertEquals("Im sorry, I do not understand what you mean. Please re-enter:", e.getMessage());
        }
    }

}
