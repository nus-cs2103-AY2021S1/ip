package duke.utils;

import duke.command.*;
import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest{

    @Test
    void parseAddCommand() {
        String commandLine = "todo borrow book";

        try {
            assertEquals(Parser.parse(commandLine).getClass(), AddCommand.class);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseExitCommand() {
        String commandLine = "bye";

        try {
            assertEquals(Parser.parse(commandLine).getClass(), ExitCommand.class);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseDeleteCommand() {
        String commandLine = "delete 1";

        try {
            assertEquals(Parser.parse(commandLine).getClass(), DeleteCommand.class);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseDoneCommand() {
        String commandLine = "done 1";

        try {
            assertEquals(Parser.parse(commandLine).getClass(), DoneCommand.class);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseListCommand() {
        String commandLine = "list";

        try {
            assertEquals(Parser.parse(commandLine).getClass(), ListCommand.class);
        } catch (DukeException e) {
            fail();
        }
    }

}
