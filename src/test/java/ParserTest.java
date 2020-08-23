import duke.command.*;
import duke.exception.DukeException;
import main.java.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void canExitDuke_bye_byeCommand() {
        try {
            assertTrue(Parser.parse("bye") instanceof ByeCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markTestAsDone_done1_DoneCommand() {
        try {
            assertTrue(Parser.parse("done 1") instanceof DoneCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void invalidDoneNumber_doneA_throwException() {
        try {
            Parser.parse("done A");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tTask number format invalid, must be a number.");
        }
    }

    @Test
    public void emptyDoneNumber_done_throwException() {
        try {
            Parser.parse("done          ");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tNo task number specified.");
        }
    }

    @Test
    public void deleteTask_delete1_DeleteCommand() {
        try {
            assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void invalidDeleteNumber_deleteA_throwException() {
        try {
            Parser.parse("delete A");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tTask number format invalid, must be a number.");
        }
    }

    @Test
    public void emptyDeleteNumber_delete_throwException() {
        try {
            Parser.parse("delete          ");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tNo task number specified.");
        }
    }

    @Test
    public void listTasks_list_ListCommand() {
        try {
            assertTrue(Parser.parse("list") instanceof ListCommand);
        } catch(DukeException e) {
            fail();
        }
    }

    @Test
    public void listTasksOnDate_tasksOn30082001_TasksOnCommand() {
        try {
            assertTrue(Parser.parse("tasks on 30/08/2001") instanceof TasksOnCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void invalidDateFormat_tasksOn3082001_throwException() {
        try {
            Parser.parse("tasks on 30/8/2001");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tDate should be in format dd/mm/yyyy");
        }
    }

    @Test
    public void emptyDate_tasksOn_throwException() {
        try {
            Parser.parse("tasks on          ");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tNeed to specify the date of the tasks");
        }
    }

    @Test
    public void addTodo_todoWashCar_AddCommand() {
        try {
            assertTrue(Parser.parse("todo wash car") instanceof AddCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void todoNoDescription_todo_throwException() {
        try {
            Parser.parse("todo       ");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tThe description of a todo cannot be empty.");
        }
    }

    @Test
    public void addDeadline_deadlineReturnBookTiming_AddCommand() {
        try {
            assertTrue(Parser.parse("deadline return book /by 22/08/2020 18:00") instanceof AddCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void deadlineNoDescription_deadlineTiming_throwException() {
        try {
            Parser.parse("deadline          /by 22/08/2020 18:00");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tThe description of a deadline cannot be empty.");
        }
    }

    @Test
    public void deadlineNoTiming_deadlineReturnBook_throwException() {
        try {
            Parser.parse("deadline return book /by         ");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tThe date of a deadline cannot be empty.");
        }
    }

    @Test
    public void deadlineDateInvalid_deadlineReturnBookInvalidTiming_throwException() {
        try {
            Parser.parse("deadline return book /by 22/8/2020 1800");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tDate should be in format dd/mm/yyyy hh:mm");
        }
    }

    @Test
    public void addEvent_eventReturnBookTiming_AddCommand() {
        try {
            assertTrue(Parser.parse("event return book /at 22/08/2020 18:00") instanceof AddCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void eventNoDescription_deadlineTiming_throwException() {
        try {
            Parser.parse("event          /at 22/08/2020 18:00");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tThe description of an event cannot be empty.");
        }
    }

    @Test
    public void eventNoTiming_eventReturnBook_throwException() {
        try {
            Parser.parse("event return book /at         ");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tThe date of an event cannot be empty.");
        }
    }

    @Test
    public void eventDateInvalid_eventReturnBookInvalidTiming_throwException() {
        try {
            Parser.parse("event return book /at 22/8/2020 1800");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tDate should be in format dd/mm/yyyy hh:mm");
        }
    }

    @Test
    public void randomCommand_Hello_throwException() {
        try {
            Parser.parse("Hello");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\tI don't know what that means :-(");
        }
    }

    @Test
    public void pressEnter_enter_DoNothingCommand() {
        try {
            assertTrue(Parser.parse("\n") instanceof DoNothingCommand);
        } catch (DukeException e) {
            fail();
        }
    }
}
