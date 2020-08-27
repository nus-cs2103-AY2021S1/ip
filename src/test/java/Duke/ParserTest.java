package Duke;

import Duke.Command.*;
import Duke.Exception.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUP() {
        parser = new Parser();
    }

    private <T extends Command> void parseAndAssertCommandType(String input, Class<T> expectedCommandClass) throws DukeException, IOException {
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
        assertThrows(InvalidInputException.class,() -> parser.parse(input));
    }
}
