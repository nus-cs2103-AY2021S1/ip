package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUP() {
        parser = new Parser();
    }

    private <T extends Command> void parseAndAssertCommandType(String input, Class<T> expectedCommandClass)
            throws DukeException {
        Command result = parser.parse(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
    }

    @Test
    public void parse_exitCommand_parsedCorrectly() throws DukeException, IOException {
        String input = "bye";
        parseAndAssertCommandType(input, ExitCommand.class);
    }

    @Test
    public void parse_listCommand_parsedCorrectly() throws DukeException, IOException {
        String input = "list";
        parseAndAssertCommandType(input, ListCommand.class);
    }

    @Test
    public void parse_command_parsedIncorrectly() throws DukeException, IOException {
        String input = "bad command";
        assertThrows(InvalidInputException.class, () -> parser.parse(input));
    }
}
