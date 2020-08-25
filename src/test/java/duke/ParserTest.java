package duke;

import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidDateException;
import duke.exception.NoIndexException;
import duke.exception.UnrecognizedTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    @Test
    public void parse_unrecognizedCommand_exceptionThrown1() {
        assertThrows(UnrecognizedTaskException.class, () -> {
            Parser.parse("bleh");
        });
    }

    @Test
    public void parse_unrecognizedCommand_exceptionThrown2() {
        assertThrows(UnrecognizedTaskException.class, () -> {
            Parser.parse("bleh kdfhkdh");
        });
    }

    @Test
    public void parse_deleteNoIndex_exceptionThrown() {
        assertThrows(NoIndexException.class, () -> {
            Parser.parse("delete");
        });
    }

    @Test
    public void parse_doneNoIndex_exceptionThrown() {
        assertThrows(NoIndexException.class, () -> {
            Parser.parse("done ");
        });
    }

    @Test
    public void parse_eventNoTaskDescription_exceptionThrown() {
        assertThrows(EmptyTaskException.class, () -> {
            Parser.parse("event ");
        });
    }

    @Test
    public void parse_deadlineNoTaskDescription_exceptionThrown() {
        assertThrows(EmptyTaskException.class, () -> {
            Parser.parse("deadline");
        });
    }

    @Test
    public void getDateTime_invalidDateFormat_exceptionThrown() {
            assertThrows(InvalidDateException.class, () -> {
            Parser.getDateTime("08102020");
        });
    }

    @Test
    public void getDateTime_invalidDateTimeFormat_exceptionThrown() {
        assertThrows(InvalidDateException.class, () -> {
            Parser.getDateTime("2020-08-20T10.00");
        });
    }

    @Test
    public void parse_inputToDo_returnToDoCommand() {
        try {
            assertEquals(Parser.parse("todo sleep"), new ToDoCommand("sleep"));
        }  catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parse_inputEvent_returnEventCommand() {
        try {
            assertEquals(Parser.parse("event bfast /at 10:00"),
                    new EventCommand("bfast /at 10:00"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parse_inputDeadline_returnDeadlineCommand() {
        try {
            assertEquals(Parser.parse("deadline project /by 2020-08-27"),
                    new DeadlineCommand("project /by 2020-08-27"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parse_inputDone_returnDoneCommand() {
        try {
            assertEquals(Parser.parse("done 1"), new DoneCommand(1));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parse_inputDelete_returnDeleteCommand() {
        try {
            assertEquals(Parser.parse("delete 1"), new DeleteCommand(1));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
