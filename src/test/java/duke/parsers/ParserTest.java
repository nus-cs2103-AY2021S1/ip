package duke.parsers;

import duke.commands.*;
import duke.exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_bye_success() {
        Command command = Parser.parse("bye");
        Command commandWithWhiteSpace = Parser.parse("    bye   ");
        Command commandWithCaps = Parser.parse("Bye");
        assertTrue(command instanceof ByeCommand);
        assertTrue(commandWithWhiteSpace instanceof ByeCommand);
        assertTrue(commandWithCaps instanceof ByeCommand);
    }

    @Test
    public void parse_list_success() {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parse_done_success() {
        Command command = Parser.parse("done 1");
        assertTrue(command instanceof DoneCommand);
    }

    @Test
    public void parse_doneWithEmptyTask_exceptionThrown() {
        try {
            Parser.parse("done");
            fail();
        } catch (EmptyTaskDoneException e) {
            assertEquals("OOPS! Task done cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void parse_doneWithInvalidTask_exceptionThrown() {
        try {
            Parser.parse("done task");
            fail();
        } catch (InvalidTaskException e) {
            assertEquals("OOPS! Invalid task found.", e.getMessage());
        }
    }

    @Test
    public void parse_delete_success() {
        Command command = Parser.parse("delete 2");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parse_deleteWithEmptyTask_exceptionThrown() {
        try {
            Parser.parse("delete");
            fail();
        } catch (EmptyTaskDeletedException e) {
            assertEquals("OOPS! Task deleted cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void parse_deleteWithInvalidTask_exceptionThrown() {
        try {
            Parser.parse("delete task");
            fail();
        } catch (InvalidTaskException e) {
            assertEquals("OOPS! Invalid task found.", e.getMessage());
        }
    }

    @Test
    public void parse_deadline_success() {
        Command command = Parser.parse("deadline return book /by 23/8/2020 1:00 PM");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_deadlineWithInvalidDate_exceptionThrown() {
        try {
            Parser.parse("deadline return book /by some-date");
            fail();
        } catch (DukeDateTimeParseException e) {
            assertEquals("OOPS! Invalid date / time format!", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithDescriptionButNoDate_exceptionThrown() {
        try {
            Parser.parse("deadline return book");
            fail();
        } catch (EmptyDueDateException e) {
            assertEquals("OOPS! The due date of deadline cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithNoDescription_exceptionThrown() {
        try {
            Parser.parse("deadline /by 23/8/2020");
            fail();
        } catch (EmptyTaskDescriptionException e) {
            assertEquals("OOPS! The description of a deadline cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_todo_success() {
        Command command = Parser.parse("todo task");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_todoWithEmptyDescription_exceptionThrown() {
        try {
            Parser.parse("todo    ");
            fail();
        } catch (EmptyTaskDescriptionException e) {
            assertEquals("OOPS! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_event_success() {
        Command command = Parser.parse("event meeting /at 23/8/2020");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_today_success() {
        Command command = Parser.parse("today");
        assertTrue(command instanceof TodayCommand);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            Parser.parse("hello");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("OOPS! I'm sorry but I don't know what that means :-(", e.getMessage());
        }
    }
}
