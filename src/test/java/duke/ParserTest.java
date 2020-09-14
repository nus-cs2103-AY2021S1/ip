package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void parse_unknownCommand_exceptionThrown() {
        try {
            Parser.parse("unknown");
        } catch (DukeException e) {
            assertEquals("Sorry, I don't understand what you just said.", e.getMessage());
        }
    }

    @Test
    public void parse_doneWithNoTaskIndex_exceptionThrown() {
        try {
            Parser.parse("done ");
        } catch (DukeException e) {
            assertEquals("Error! 'done' description cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_doneWithNonIntegerValue_exceptionThrown() {

    }

    @Test
    public void parse_todoWithNoDescription_exceptionThrown() {
        try {
            Parser.parse("todo ");
        } catch (DukeException e) {
            assertEquals("Error! 'todo' description cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithNoDescription_exceptionThrown() {
        try {
            Parser.parse("deadline ");
        } catch (DukeException e) {
            assertEquals("Error! 'deadline' description cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithNoTaskDescription_exceptionThrown() {
        try {
            Parser.parse("deadline /by 2020-12-12 1000");
        } catch (DukeException e) {
            assertEquals("Error! No task description provided.", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithNoDateTime_exceptionThrown() {
        try {
            Parser.parse("deadline this");
        } catch (DukeException e) {
            assertEquals("Error! '/by' date not found.", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithWrongDateTimeFormat_exceptionThrown() {
        try {
            Parser.parse("deadline this /by 2020/12/12 10:00");
        } catch (DukeException e) {
            assertEquals("Error! Date/time is in the wrong format.", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithInvalidDateTime_exceptionThrown() {
        try {
            Parser.parse("deadline this /by 2020-13-12 3300");
        } catch (DukeException e) {
            assertEquals("Error! Date/time is invalid.", e.getMessage());
        }
    }
}
