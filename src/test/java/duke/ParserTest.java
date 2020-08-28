package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

 class ParserTest {

    @Test
     void parse_noDescription_exceptionThrown() {
        try {
            Parser.parse("event");
            fail();
        } catch (DukeException e) {
            assertEquals("     OOPS!!! The description of a event cannot be empty.", e.getMessage());
        }
    }

    @Test
     void parse_wrongCommand_exceptionThrown() {
        try {
            Parser.parse("random");
            fail();
        } catch (DukeException e) {
            assertEquals("     OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}