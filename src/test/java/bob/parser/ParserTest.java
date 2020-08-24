package bob.parser;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import seedu.bob.commands.*;

import seedu.bob.exceptions.BobEmptyFindException;
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
    public void parse_exitCommand_parsedCorrectly()
            throws BobEmptyTaskException, BobInvalidCommandException, BobEmptyFindException {
        String input = "bye";
        parseAndAssertCommandType(input, ExitCommand.class);
    }

    @Test
    public void parse_listCommand_parsedCorrectly()
            throws BobEmptyTaskException, BobInvalidCommandException, BobEmptyFindException {
        String input = "list";
        parseAndAssertCommandType(input, ListCommand.class);
    }

    @Test
    public void parse_todoCommand_parsedCorrectly()
            throws BobEmptyTaskException, BobInvalidCommandException, BobEmptyFindException {
        String input = "todo work";
        parseAndAssertCommandType(input, TodoCommand.class);
    }

    @Test
    public void parse_eventCommand_parsedCorrectly()
            throws BobEmptyTaskException, BobInvalidCommandException, BobEmptyFindException {
        String input = "event birthday /at 2020/09/12 0000";
        parseAndAssertCommandType(input, EventCommand.class);
    }

    @Test
    public void parse_deadlineCommand_parsedCorrectly()
            throws BobEmptyTaskException, BobInvalidCommandException, BobEmptyFindException {
        String input = "deadline project /by 2020/08/27 1730";
        parseAndAssertCommandType(input, DeadlineCommand.class);
    }

    @Test
    public void parse_doneCommand_parsedCorrectly()
            throws BobEmptyTaskException, BobInvalidCommandException, BobEmptyFindException {
        String input = "done 1";
        parseAndAssertCommandType(input, DoneCommand.class);
    }

    @Test
    public void parse_deleteCommand_parsedCorrectly()
            throws BobEmptyTaskException, BobInvalidCommandException, BobEmptyFindException {
        String input = "delete 1";
        parseAndAssertCommandType(input, DeleteCommand.class);
    }

    @Test
    public void parse_findCommand_parsedCorrectly()
            throws BobEmptyTaskException, BobInvalidCommandException, BobEmptyFindException {
        String input = "find test";
        parseAndAssertCommandType(input, FindCommand.class);
    }

    /* Test incorrect parsing */

    @Test
    public void parse_command_parsedIncorrectly() {
        String input = "bad command";
        assertThrows(BobInvalidCommandException.class,() -> parser.parse(input));
    }

    @Test
    public void parse_todoCommand_parsedNoTaskDescription() {
        String input = "todo";
        assertThrows(BobEmptyTaskException.class,() -> parser.parse(input));
    }

    @Test
    public void parse_eventCommand_parsedNoTaskDescription() {
        String input = "event";
        assertThrows(BobEmptyTaskException.class,() -> parser.parse(input));
    }

    @Test
    public void parse_deadlineCommand_parsedNoTaskDescription() {
        String input = "deadline";
        assertThrows(BobEmptyTaskException.class,() -> parser.parse(input));
    }

    @Test
    public void parse_findCommand_parsedNoFindDescription() {
        String input = "find   ";
        assertThrows(BobEmptyFindException.class,() -> parser.parse(input));
    }

    private <T extends Command> void parseAndAssertCommandType(String input, Class<T> expectedCommandClass)
            throws BobEmptyTaskException, BobInvalidCommandException, BobEmptyFindException {
        Command result = parser.parse(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
    }
}
