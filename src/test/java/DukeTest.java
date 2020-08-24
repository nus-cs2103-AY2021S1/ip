import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    
    @Test
    public void newToDo_emptyTaskName_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            ToDo.createNewToDo("");
        });
        
        String expected = "Task name cannot be empty!";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newDeadline_invalidArguments_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            Deadline.createNewDeadline("a b c");
        });

        String expected = "Invalid arguments for a new deadline.";
        assertTrue(e.getMessage().contains(expected));
    }


    @Test
    public void newDeadline_blankName_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            Deadline.createNewDeadline(" /by 2020-12-12");
        });

        String expected = "Deadline name cannot be blank!";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newDeadline_blankTime_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            Deadline.createNewDeadline("name /by  ");
        });

        String expected = "Deadline time cannot be blank!";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newDeadline_invalidDateTimeFormat_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            Deadline.createNewDeadline("name /by 2020-13-13");
        });

        String expected = "DateTime format is invalid.";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newEvent_invalidArguments_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            Event.createNewEvent("a b c");
        });

        String expected = "Invalid arguments for a new event.";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newEvent_blankName_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            Event.createNewEvent(" /at 2020-12-12");
        });

        String expected = "Event name cannot be blank!";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newEvent_blankTime_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            Event.createNewEvent("name /at  ");
        });

        String expected = "Event time cannot be blank!";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newEvent_invalidDateTimeFormat_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            Event.createNewEvent("name /at 2020-13-13");
        });

        String expected = "DateTime format is invalid.";
        assertTrue(e.getMessage().contains(expected));
    }
}