package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.exception.DukeInputException;

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

    @Test
    public void parse_correctListCommandInput_listCommand() {
        try {
            assertEquals(Parser.parse("list").toString(), (new ListCommand()).toString());
        } catch (DukeInputException e) {
            fail();
        }
    }

    @Test
    public void parse_wrongListCommandInput_exceptionThrown() {
        try {
            Parser.parse("list 123");
            fail();
        } catch (DukeInputException e) {
            assertEquals("DUKE ERROR - Invalid command <list 123> given.", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

}
