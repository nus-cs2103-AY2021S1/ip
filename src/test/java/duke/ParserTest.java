package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void parse_noDescription_exceptionThrown() {
        try {
            Parser.parse("event");
            fail();
        } catch (DukeException e) {
            assertEquals("PIII!!! The description of a/n event cannot be empty. \uD83D\uDC80", e.getMessage());
        }
    }

    @Test
    void parse_wrongCommand_exceptionThrown() {
        try {
            Parser.parse("random");
            fail();
        } catch (DukeException e) {
            assertEquals("PIII!!! I'm sorry, but I don't know what that means. \uD83D\uDC80", e.getMessage());
        }
    }
}
