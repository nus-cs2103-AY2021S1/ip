package duke.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.UserCommand;


class ParserTest {

    @Test
    public void parseByeCommand() {
        UserCommand command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);

    }

    @Test
    public void parseListCommand() {
        UserCommand command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);

    }

}
