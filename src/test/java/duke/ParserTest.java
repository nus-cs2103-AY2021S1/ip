package duke;

import duke.command.ByeCommand;
import duke.exception.DukeInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_correctByeCommandInput_byeCommand() {
        try {
            assertEquals(Parser.parse("bye").toString(), (new ByeCommand()).toString());
        } catch (DukeInputException e) {
            fail();
        }
    }

    @Test
    public void parse_wrongByeCommandInput_exceptionThrown() {
        try {
            Parser.parse("bye bye");
            fail();
        } catch (DukeInputException e) {
            assertEquals("DUKE ERROR - Invalid command <bye bye> given.", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

}
