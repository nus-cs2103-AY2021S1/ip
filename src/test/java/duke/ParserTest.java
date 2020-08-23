package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.InvalidTaskIdException;
import duke.exception.MissingKeywordException;
import duke.exception.MissingTaskDetailsException;
import duke.exception.MissingTaskIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseList_validLowercase_listCommand() throws DukeException {
        Command c = Parser.parse("list");
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void parseList_validUppercase_listCommand() throws DukeException {
        Command c = Parser.parse("LIST");
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void parseList_validMixedCase_listCommand() throws DukeException {
        Command c = Parser.parse("LiSt");
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void parseList_leadingAndTrailingSpaces_listCommand() throws DukeException {
        Command c = Parser.parse("    list   ");
        assertTrue(c instanceof ListCommand);
    }

    // Find
    @Test
    public void parseFind_missingKeyword_exceptionThrown() {
        try {
            Command c = Parser.parse("find");
        } catch (DukeException e) {
            assertTrue(e instanceof MissingKeywordException);
            assertEquals(
                    "☹ OOPS!!! I'm not sure what tasks to search for... Please specify a keyword!",
                    e.getMessage());
        }
    }


    @Test
    public void parseFind_validLowercase_findCommand() throws DukeException {
        Command c = Parser.parse("find blahblah");
        assertTrue(c instanceof FindCommand);
    }

    @Test
    public void parseFind_validUppercase_findCommand() throws DukeException {
        Command c = Parser.parse("FIND blahblah");
        assertTrue(c instanceof FindCommand);
    }

    @Test
    public void parseFind_validMixedCase_findCommand() throws DukeException {
        Command c = Parser.parse("FiNd blahblah");
        assertTrue(c instanceof FindCommand);
    }

    // Done
    @Test
    public void parseDone_missingTaskId_exceptionThrown() {
        try {
            Command c = Parser.parse("done");
        } catch (DukeException e) {
            assertTrue(e instanceof MissingTaskIdException);
            assertEquals("☹ OOPS!!! Failed to mark task as complete! No task was specified!",
                    e.getMessage());
        }
    }

    @Test
    public void parseDone_validLowercase_doneCommand() throws DukeException {
        Command c = Parser.parse("done 1");
        assertTrue(c instanceof DoneCommand);
    }

    @Test
    public void parseDone_validUppercase_doneCommand() throws DukeException {
        Command c = Parser.parse("DONE 1");
        assertTrue(c instanceof DoneCommand);
    }

    @Test
    public void parseDone_validMixedCase_doneCommand() throws DukeException {
        Command c = Parser.parse("DoNe 1");
        assertTrue(c instanceof DoneCommand);
    }

    @Test
    public void parseDone_leadingAndTrailingSpaces_doneCommand() throws DukeException {
        Command c = Parser.parse("    done   1  ");
        assertTrue(c instanceof DoneCommand);
    }

    @Test
    public void parseDone_invalidTaskId_exceptionThrown() {
        try {
            Command c = Parser.parse("done task1");
        } catch (DukeException e) {
            assertTrue(e instanceof InvalidTaskIdException);
            assertEquals(
                    "☹ OOPS!!! Failed to mark task as complete! Please key in only the integer " +
                            "representing the task!", e.getMessage());
        }
    }

    // Delete
    @Test
    public void parseDelete_missingTaskId_exceptionThrown() {
        try {
            Command c = Parser.parse("delete");
        } catch (DukeException e) {
            assertTrue(e instanceof MissingTaskIdException);
            assertEquals("☹ OOPS!!! Failed to delete task! No task was specified!", e.getMessage());
        }
    }

    @Test
    public void parseDelete_validLowercase_deleteCommand() throws DukeException {
        Command c = Parser.parse("delete 1");
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    public void parseDelete_validUppercase_deleteCommand() throws DukeException {
        Command c = Parser.parse("DELETE 1");
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    public void parseDelete_validMixedCase_deleteCommand() throws DukeException {
        Command c = Parser.parse("DeLeTe 1");
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    public void parseDelete_leadingAndTrailingSpaces_deleteCommand() throws DukeException {
        Command c = Parser.parse("    delete    1   ");
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    public void parseDelete_invalidTaskId_exceptionThrown() {
        try {
            Command c = Parser.parse("delete task1");
        } catch (DukeException e) {
            assertTrue(e instanceof InvalidTaskIdException);
            assertEquals("☹ OOPS!!! Failed to delete task! Please key in only the integer " +
                    "representing the task!", e.getMessage());
        }
    }

    // To do, Deadline, Event
    @Test
    public void parseTodo_missingDetails_exceptionThrown() {
        try {
            Command c = Parser.parse("todo");
        } catch (DukeException e) {
            assertTrue(e instanceof MissingTaskDetailsException);
            assertEquals("☹ OOPS!!! Failed to create task! Insufficient details provided!",
                    e.getMessage());
        }
    }

    @Test
    public void parseDeadline_missingDetails_exceptionThrown() {
        try {
            Command c = Parser.parse("deadline");
        } catch (DukeException e) {
            assertTrue(e instanceof MissingTaskDetailsException);
            assertEquals("☹ OOPS!!! Failed to create task! Insufficient details provided!",
                    e.getMessage());
        }
    }

    @Test
    public void parseEvent_missingDetails_exceptionThrown() {
        try {
            Command c = Parser.parse("event");
        } catch (DukeException e) {
            assertTrue(e instanceof MissingTaskDetailsException);
            assertEquals("☹ OOPS!!! Failed to create task! Insufficient details provided!",
                    e.getMessage());
        }
    }

    @Test
    public void parseTodo_validDetails_addCommand() throws DukeException {
        Command c = Parser.parse("todo blahblah");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parseTodo_validDetailsMultipleWords_addCommand() throws DukeException {
        Command c = Parser.parse("todo blahblah blah");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parseTodo_leadingAndTrailingSpaces_addCommand() throws DukeException {
        Command c = Parser.parse("    todo    blahblah    ");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parseDeadline_missingDeadline_exceptionThrown() {
        try {
            Command c = Parser.parse("deadline blahblah");
        } catch (DukeException e) {
            assertTrue(e instanceof MissingTaskDetailsException);
            assertEquals("☹ OOPS!!! Failed to create Deadline task! No deadline was specified!",
                    e.getMessage());
        }
    }

    @Test
    public void parseDeadline_validDetails_addCommand() throws DukeException {
        Command c = Parser.parse("deadline blahblah /by 2020-10-30");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parseDeadline_leadingAndTrailingSpaces_addCommand() throws DukeException {
        Command c = Parser.parse("    deadline     blahblah  /by    2020-10-30  ");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parseEvent_missingDate_exceptionThrown() {
        try {
            Command c = Parser.parse("event blahblah");
        } catch (DukeException e) {
            assertTrue(e instanceof MissingTaskDetailsException);
            assertEquals("☹ OOPS!!! Failed to create Event task! No date was specified!",
                    e.getMessage());
        }
    }

    @Test
    public void parseEvent_validDetails_addCommand() throws DukeException {
        Command c = Parser.parse("event blahblah /at 2020-10-30");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parseEvent_leadingAndTrailingSpaces_addCommand() throws DukeException {
        Command c = Parser.parse("    event     blahblah  /at    2020-10-30  ");
        assertTrue(c instanceof AddCommand);
    }

    // Bye
    @Test
    public void parseBye_validLowercase_byeCommand() throws DukeException {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ByeCommand);
    }

    @Test
    public void parseBye_validUppercase_byeCommand() throws DukeException {
        Command c = Parser.parse("BYE");
        assertTrue(c instanceof ByeCommand);
    }

    @Test
    public void parseBye_validMixedCase_byeCommand() throws DukeException {
        Command c = Parser.parse("ByE");
        assertTrue(c instanceof ByeCommand);
    }

    // Unknown command
    @Test
    public void parseUnknown_help_exceptionThrown() {
        try {
            Command c = Parser.parse("help");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                    e.getMessage());
        }
    }

    @Test
    public void parseUnknown_emptyString_exceptionThrown() {
        try {
            Command c = Parser.parse("");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                    e.getMessage());
        }
    }

    @Test
    public void parseUnknown_todoTypo_exceptionThrown() {
        try {
            Command c = Parser.parse("todoo");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                    e.getMessage());
        }
    }
}
