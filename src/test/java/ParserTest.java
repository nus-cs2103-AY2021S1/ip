import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.logic.Parser;
import duke.logic.commands.AddCommand;
import duke.logic.commands.DeleteCommand;
import duke.logic.commands.DoneCommand;
import duke.logic.commands.ExitCommand;
import duke.logic.commands.HelpCommand;
import duke.logic.commands.ListCommand;

public class ParserTest {
    @Test
    public void parse_exitCommand_success() throws DukeException {
        assertEquals(true, Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parse_helpCommand_success() throws DukeException {
        assertEquals(true, Parser.parse("help") instanceof HelpCommand);
    }

    @Test
    public void parse_listCommand_success() throws DukeException {
        assertEquals(true, Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parse_doneCommand_success() throws DukeException {
        assertEquals(true, Parser.parse("done 1") instanceof DoneCommand);
    }

    @Test
    public void parse_doneCommand_exceptionThrown() {
        try {
            Parser.parse("done");
            fail();
        } catch (DukeException ex) {
            String error = "UHOH! I ain't sure which task to mark done if you ain't sayin'!\n"
                    + "Try somethin' like this: \ndone 1";
            assertEquals(error, ex.getMessage());
        }
    }

    @Test
    public void parse_deleteCommand_success() throws DukeException {
        assertEquals(true, Parser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void parse_deleteCommand_exceptionThrown() {
        try {
            Parser.parse("delete");
            fail();
        } catch (DukeException ex) {
            String error = "UHOH! I ain't sure which task to delete if you ain't sayin'!\n"
                    + "Try somethin' like this: \ndelete 1";
            assertEquals(error, ex.getMessage());
        }
    }

    @Test
    public void parse_addCommand_success() throws DukeException {
        assertEquals(true, Parser.parse("todo read") instanceof AddCommand);
        assertEquals(true,
                Parser.parse("deadline project /by 2019-10-15 2359") instanceof AddCommand);
        assertEquals(true,
                Parser.parse("event meeting /at 2019-10-15 1200") instanceof AddCommand);
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() {
        try {
            Parser.parse("hello");
            fail();
        } catch (DukeException ex) {
            String error = "UHOH! Boy, I don't get what y'all are sayin'!\n"
                    + "Try typin' \"help\" to see what commands you can use!";
            assertEquals(error, ex.getMessage());
        }
    }
}
