package duke.parser;

import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.UserCommand;
import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    public void parseByeCommand() throws DukeException {
        UserCommand command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);

    }

    @Test
    public void parseListCommand() throws DukeException {
        UserCommand command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);

    }


}