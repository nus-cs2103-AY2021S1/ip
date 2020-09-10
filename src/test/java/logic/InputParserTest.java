package logic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exception.InvalidInstructionFormatException;
import duke.exception.InvalidInstructionLengthException;
import duke.exception.MissingFieldException;
import duke.exception.UnknownInstructionException;
import duke.logic.UserInputParser;

public class InputParserTest {
    @Test
    public void testParseTodo()
            throws InvalidInstructionLengthException, InvalidInstructionFormatException,
            MissingFieldException, UnknownInstructionException {
        Command command = UserInputParser.parse("todo homework");
        assertTrue(command instanceof AddCommand);
        assertThrows(InvalidInstructionLengthException.class, (
                ) -> UserInputParser.parse("todo"));
    }

    @Test
    public void testParseDeadline()
            throws InvalidInstructionLengthException, InvalidInstructionFormatException,
            MissingFieldException, UnknownInstructionException {
        Command command = UserInputParser.parse("deadline CS2103 webcast /by 12/12/2020 06/06/00");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testParseDeadlineMissingField() {
        assertThrows(MissingFieldException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by"));
        assertThrows(MissingFieldException.class, (
        ) -> UserInputParser.parse("deadline /by 12/12/2020 06/06/00\n"));
        assertThrows(MissingFieldException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by 12/12/2020"));
    }

    @Test
    public void testParseDeadlineNegativeDateTime() {
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by -12/12/2020 06/06/00\n"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by 12/-12/2020 06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by 12/12/-2020 06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by 12/12/2020 -06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by 12/12/2020 06/-06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by 12/12/2020 06/06/-06"));
    }

    @Test
    public void testParseDeadlineInvalidDateTime() {
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by 0/12/2020 06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by 12/0/2020 06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by 12/13/2020 06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by 50/12/2020 06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by 12/12/2020 90/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by 12/12/2020 06/120/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /by 12/12/2020 06/06/120"));

        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("deadline CS2103 webcast /at 12/12/2020 06/06/120"));
    }

    @Test
    public void testParseEvent()
            throws InvalidInstructionLengthException, InvalidInstructionFormatException,
            MissingFieldException, UnknownInstructionException {
        Command command = UserInputParser.parse("event CS2100 Lab /at 12/12/2020 06/06/00\n");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testParseEventMissingField() {
        assertThrows(MissingFieldException.class, (
        ) -> UserInputParser.parse("event CS2100 Lab /at"));
        assertThrows(MissingFieldException.class, (
        ) -> UserInputParser.parse("event /at 12/12/2020 06/06/00\n"));
        assertThrows(MissingFieldException.class, (
        ) -> UserInputParser.parse("event CS2100 Lab /at 12/12/2020\n"));
    }

    @Test
    public void testParseEventNegativeDateTime() {
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /at -12/12/2020 06/06/00\n"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /at 12/-12/2020 06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /at 12/12/-2020 06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /at 12/12/2020 -06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /at 12/12/2020 06/-06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /at 12/12/2020 06/06/-06"));
    }

    @Test
    public void testParseEventInvalidDateTime() {
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /at 0/12/2020 06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /at 12/0/2020 06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /at 12/13/2020 06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /at 50/12/2020 06/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /at 12/12/2020 90/06/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /at 12/12/2020 06/120/00"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /at 12/12/2020 06/06/120"));

        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("event CS2103 webcast /by 12/12/2020 06/06/120"));
    }

    @Test
    public void parseDoneTest() throws InvalidInstructionLengthException,
            InvalidInstructionFormatException, MissingFieldException, UnknownInstructionException {
        Command command = UserInputParser.parse("done 1");
        assertTrue(command instanceof DoneCommand);
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> UserInputParser.parse("done"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("done done"));
    }

    @Test
    public void parseDeleteTest() throws InvalidInstructionLengthException,
            InvalidInstructionFormatException, MissingFieldException, UnknownInstructionException {
        Command command = UserInputParser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> UserInputParser.parse("delete"));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> UserInputParser.parse("delete all"));
    }

    @Test
    public void parseListTest() throws InvalidInstructionLengthException,
            InvalidInstructionFormatException, MissingFieldException, UnknownInstructionException {
        Command command = UserInputParser.parse("list");
        assertTrue(command instanceof ListCommand);
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> UserInputParser.parse("list 1"));
    }

    @Test
    public void parseHelpTest() throws InvalidInstructionLengthException,
            InvalidInstructionFormatException, MissingFieldException, UnknownInstructionException {
        Command command = UserInputParser.parse("help");
        assertTrue(command instanceof HelpCommand);
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> UserInputParser.parse("help 1"));
    }

    @Test
    public void parseByeTest() throws InvalidInstructionLengthException,
            InvalidInstructionFormatException, MissingFieldException, UnknownInstructionException {
        Command command = UserInputParser.parse("bye");
        assertTrue(command instanceof ExitCommand);
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> UserInputParser.parse("bye 1"));
    }

    @Test
    public void parseFindTest() throws InvalidInstructionLengthException,
            InvalidInstructionFormatException, MissingFieldException, UnknownInstructionException {
        Command command = UserInputParser.parse("find key");
        assertTrue(command instanceof FindCommand);
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> UserInputParser.parse("find"));
    }
}
