package bob.parser;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import seedu.bob.commands.Command;
import seedu.bob.commands.TodoCommand;
import seedu.bob.commands.EventCommand;
import seedu.bob.commands.DeadlineCommand;
import seedu.bob.commands.DoneCommand;
import seedu.bob.commands.DeleteCommand;
import seedu.bob.commands.ListCommand;
import seedu.bob.commands.ExitCommand;

import seedu.bob.exceptions.BobEmptyTaskException;
import seedu.bob.exceptions.BobInvalidCommandException;
import seedu.bob.parser.Parser;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    /* Tests correct parsing */

    @Test
    public void parse_exitCommand_parsedCorrectly() throws BobEmptyTaskException, BobInvalidCommandException {
        String input = "bye";
        parseAndAssertCommandType(input, ExitCommand.class);
    }

    @Test
    public void parse_listCommand_parsedCorrectly() throws BobEmptyTaskException, BobInvalidCommandException {
        String input = "list";
        parseAndAssertCommandType(input, ListCommand.class);
    }

    @Test
    public void parse_todoCommand_parsedCorrectly() throws BobEmptyTaskException, BobInvalidCommandException {
        String input = "todo work";
        parseAndAssertCommandType(input, TodoCommand.class);
    }

    @Test
    public void parse_eventCommand_parsedCorrectly() throws BobEmptyTaskException, BobInvalidCommandException {
        String input = "event birthday /at 2020/09/12 0000";
        parseAndAssertCommandType(input, EventCommand.class);
    }

    @Test
    public void parse_deadlineCommand_parsedCorrectly() throws BobEmptyTaskException, BobInvalidCommandException {
        String input = "deadline project /by 2020/08/27 1730";
        parseAndAssertCommandType(input, DeadlineCommand.class);
    }

    @Test
    public void parse_doneCommand_parsedCorrectly() throws BobEmptyTaskException, BobInvalidCommandException {
        String input = "done 1";
        parseAndAssertCommandType(input, DoneCommand.class);
    }

    @Test
    public void parse_deleteCommand_parsedCorrectly() throws BobEmptyTaskException, BobInvalidCommandException {
        String input = "delete 1";
        parseAndAssertCommandType(input, DeleteCommand.class);
    }

    /* Test incorrect parsing */

    @Test
    public void parse_command_parsedIncorrectly() throws BobEmptyTaskException, BobInvalidCommandException {
        String input = "bad command";
        assertThrows(BobInvalidCommandException.class,() -> parser.parse(input));
    }

    @Test
    public void parse_todoCommand_parsedNoTaskDescription() throws BobEmptyTaskException, BobInvalidCommandException {
        String input = "todo";
        assertThrows(BobEmptyTaskException.class,() -> parser.parse(input));
    }

    @Test
    public void parse_eventCommand_parsedNoTaskDescription() throws BobEmptyTaskException, BobInvalidCommandException {
        String input = "event";
        assertThrows(BobEmptyTaskException.class,() -> parser.parse(input));
    }

    @Test
    public void parse_deadlineCommand_parsedNoTaskDescription() throws BobEmptyTaskException, BobInvalidCommandException {
        String input = "deadline";
        assertThrows(BobEmptyTaskException.class,() -> parser.parse(input));
    }

    private <T extends Command> void parseAndAssertCommandType(String input, Class<T> expectedCommandClass)
            throws BobEmptyTaskException, BobInvalidCommandException {
        Command result = parser.parse(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
    }
}
