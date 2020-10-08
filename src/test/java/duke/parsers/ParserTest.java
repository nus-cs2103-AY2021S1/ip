package duke.parsers;

import static duke.utils.Messages.MESSAGE_EMPTY_DUE_DATE;
import static duke.utils.Messages.MESSAGE_EMPTY_TASK_DELETED;
import static duke.utils.Messages.MESSAGE_EMPTY_TASK_DESCRIPTION;
import static duke.utils.Messages.MESSAGE_EMPTY_TASK_DONE;
import static duke.utils.Messages.MESSAGE_INVALID_COMMAND;
import static duke.utils.Messages.MESSAGE_INVALID_DATE_TIME;
import static duke.utils.Messages.MESSAGE_INVALID_TASK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.TodayCommand;
import duke.exceptions.DukeDateTimeParseException;
import duke.exceptions.EmptyDueDateException;
import duke.exceptions.EmptySearchWordException;
import duke.exceptions.EmptyTaskDeletedException;
import duke.exceptions.EmptyTaskDescriptionException;
import duke.exceptions.EmptyTaskDoneException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidTaskException;

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
            assertEquals(MESSAGE_EMPTY_TASK_DONE, e.getMessage());
        }
    }

    @Test
    public void parse_doneWithInvalidTask_exceptionThrown() {
        try {
            Parser.parse("done task");
            fail();
        } catch (InvalidTaskException e) {
            assertEquals(MESSAGE_INVALID_TASK, e.getMessage());
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
            assertEquals(MESSAGE_EMPTY_TASK_DELETED, e.getMessage());
        }
    }

    @Test
    public void parse_deleteWithInvalidTask_exceptionThrown() {
        try {
            Parser.parse("delete task");
            fail();
        } catch (InvalidTaskException e) {
            assertEquals(MESSAGE_INVALID_TASK, e.getMessage());
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
            assertEquals(MESSAGE_INVALID_DATE_TIME, e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithDescriptionButNoDate_exceptionThrown() {
        try {
            Parser.parse("deadline return book");
            fail();
        } catch (EmptyDueDateException e) {
            assertEquals(MESSAGE_EMPTY_DUE_DATE, e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithNoDescription_exceptionThrown() {
        try {
            Parser.parse("deadline /by 23/8/2020");
            fail();
        } catch (EmptyTaskDescriptionException e) {
            assertEquals(String.format(MESSAGE_EMPTY_TASK_DESCRIPTION, "deadline"), e.getMessage());
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
            assertEquals(String.format(MESSAGE_EMPTY_TASK_DESCRIPTION, "todo"), e.getMessage());
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
            assertEquals(MESSAGE_INVALID_COMMAND, e.getMessage());
        }
    }

    @Test
    public void parse_find_success() {
        Command command = Parser.parse("find something");
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void parse_findWithEmptySearchWord_exceptionThrown() {
        assertThrows(EmptySearchWordException.class, () -> Parser.parse("find"));
    }
}
