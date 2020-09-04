package duke.logic;

import duke.exceptions.DukeException;
import duke.logic.commands.AddCommand;
import duke.logic.commands.Command;
import duke.logic.commands.DeleteCommand;
import duke.logic.commands.DoneCommand;
import duke.logic.commands.ExitCommand;
import duke.logic.commands.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandParserTest {
    @Test
    public void parse_ExitCommand() throws DukeException {
        String commandString = "bye";
        Command c = CommandParser.parse(commandString);
        assertTrue(c instanceof ExitCommand);
    }

    @Test
    public void parse_ListCommand() throws DukeException {
        String commandString = "list";
        Command c = CommandParser.parse(commandString);
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void parse_DoneCommand() throws DukeException {
        String commandString = "done 1";
        Command c = CommandParser.parse(commandString);
        assertTrue(c instanceof DoneCommand);
    }

    @Test
    public void parse_DeleteCommand() throws DukeException {
        String commandString = "delete 1";
        Command c = CommandParser.parse(commandString);
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    public void parse_addCommand() throws DukeException {
        String commandString1 = "deadline foo";
        Command c1 = CommandParser.parse(commandString1);
        assertTrue(c1 instanceof AddCommand);

        String commandString2 = "event foo";
        Command c2 = CommandParser.parse(commandString1);
        assertTrue(c2 instanceof AddCommand);

        String commandString3 = "todo foo";
        Command c3 = CommandParser.parse(commandString1);
        assertTrue(c3 instanceof AddCommand);
    }
}
