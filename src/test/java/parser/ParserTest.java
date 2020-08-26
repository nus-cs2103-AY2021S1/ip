package parser;

import duke.commands.ByeCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ListCommand;
import duke.commands.AddCommand;

import duke.Parser;

import duke.exceptions.InvalidCommandException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    @DisplayName("Testing if ByeCommand is being outputted when bye is inputted")
    public void parseByeCommand() throws InvalidCommandException {
        assertEquals(Parser.parse("bye").getClass(), ByeCommand.class);
    }

    @Test
    @DisplayName("Testing if ListCommand is being outputted when list is inputted")
    public void parseListCommand() throws InvalidCommandException {
        assertEquals(Parser.parse("list").getClass(), ListCommand.class);
    }

    @Test
    @DisplayName("Testing if DoneCommand is being outputted when done is inputted")
    public void parseDoneCommand() throws InvalidCommandException {
        assertEquals(Parser.parse("done 2").getClass(), DoneCommand.class);
    }

    @Test
    @DisplayName("Testing if DeleteCommand is being outputted when delete is inputted")
    public void parseDeleteCommand() throws InvalidCommandException {
        assertEquals(Parser.parse("delete").getClass(), DeleteCommand.class);
    }

    @Test
    @DisplayName("Testing if AddCommand is being outputted when the deadline, event and todo command is inputted")
    public void parseAddCommand() throws InvalidCommandException {
        assertEquals(Parser.parse("event read book").getClass(), AddCommand.class);
        assertEquals(Parser.parse("todo read book").getClass(), AddCommand.class);
        assertEquals(Parser.parse("deadline read book").getClass(), AddCommand.class);
    }

    @Test
    @DisplayName("Testing if isTask, isDeadline, isToDo and isEvent methods are working")
    public void parseMiscellaneousCommand() throws InvalidCommandException {
        assertTrue(Parser.isEventCommand("event"));
        assertTrue(Parser.isDeadlineCommand("deadline"));
        assertTrue(Parser.isToDoCommand("todo"));
        assertTrue(Parser.isTask("todo"));
    }

    @Test
    @DisplayName("Testing if InvalidCommandException is thrown if a invalid command is inputted")
    public void parseInvalidCommand() {
        assertThrows(InvalidCommandException.class,
                () -> Parser.parse("blah"));
    }
}
