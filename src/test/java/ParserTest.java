import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.logic.commands.AddCommand;
import duke.logic.commands.Command;
import duke.logic.commands.DeleteCommand;
import duke.logic.commands.DoneCommand;
import duke.logic.commands.ExitCommand;
import duke.logic.commands.ListCommand;
import duke.logic.parser.Parser;

public class ParserTest {
    @Test
    public void isInteger_stringInteger_success() {
        assertEquals(true, Parser.isInteger("2"));
    }

    @Test
    public void isValidFormat_stringInputDate_valid() {
        assertEquals(true, Parser.isValidFormat("2020-10-10 12:30"));
    }

    @Test
    public void isValidFormat_stringInputDate_invalid() {
        assertEquals(false, Parser.isValidFormat("10th august"));

    }

    @Test
    public void parse_oneWordCommand_success() throws DukeException {
        assertEquals(true, Parser.parse("list") instanceof ListCommand);
        assertEquals(true, Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parse_doneCommand_success() throws DukeException {
        assertEquals(true, Parser.parse("done 1") instanceof DoneCommand);
        assertEquals(true, Parser.parse("done 3") instanceof DoneCommand);
    }

    @Test
    public void parse_invalidDoneCommand_exceptionThrown() {
        try {
            assertEquals(true, Parser.parse("done 0") instanceof DoneCommand);
        } catch (DukeException e) {
            assertEquals("Please enter a valid task number.", e.getMessage());
        }
    }

    @Test
    public void parse_deleteCommand_success() throws DukeException {
        assertEquals(true, Parser.parse("delete 1") instanceof DeleteCommand);
        assertEquals(true, Parser.parse("delete 3") instanceof DeleteCommand);
    }

    @Test
    public void parse_addCommand_success() throws DukeException {
        assertEquals(true, Parser.parse("todo study") instanceof AddCommand);
        assertEquals(true, Parser.parse("deadline work /by 2020-12-12 12:30") instanceof AddCommand);
        assertEquals(true, Parser.parse("event proj /at 2020-12-12 12:30") instanceof AddCommand);
    }

    @Test
    public void parse_invalidCommands_exceptionThrown() {
        try {
            assertEquals(true, Parser.parse("abcd") instanceof Command);
        } catch (DukeException e) {
            assertEquals("Oh no, didn't get that :( "
                    + "Try again or press 'help' to find out more!", e.getMessage());
        }
    }
}
