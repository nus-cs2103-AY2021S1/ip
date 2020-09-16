import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import alfred.AlfredException;
import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.ToDo;

public class AlfredTest {

    @Test
    public void newToDo_emptyTaskName_exceptionThrown() {
        Exception e = Assertions.assertThrows(AlfredException.class, () -> {
            ToDo.createNewToDo("");
        });

        String expected = "Task name cannot be empty!";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newDeadline_invalidArguments_exceptionThrown() {
        Exception e = Assertions.assertThrows(AlfredException.class, () -> {
            Deadline.createNewDeadline("a b c");
        });

        String expected = "Invalid arguments for a new deadline.";
        assertTrue(e.getMessage().contains(expected));
    }


    @Test
    public void newDeadline_blankName_exceptionThrown() {
        Exception e = Assertions.assertThrows(AlfredException.class, () -> {
            Deadline.createNewDeadline(" /by 2020-12-12");
        });

        String expected = "Deadline name cannot be blank!";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newDeadline_blankTime_exceptionThrown() {
        Exception e = Assertions.assertThrows(AlfredException.class, () -> {
            Deadline.createNewDeadline("name /by  ");
        });

        String expected = "Deadline time cannot be blank!";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newDeadline_invalidDateTimeFormat_exceptionThrown() {
        Exception e = Assertions.assertThrows(AlfredException.class, () -> {
            Deadline.createNewDeadline("name /by 2020-13-13");
        });

        String expected = "DateTime format is invalid.";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newEvent_invalidArguments_exceptionThrown() {
        Exception e = Assertions.assertThrows(AlfredException.class, () -> {
            Event.createNewEvent("a b c");
        });

        String expected = "Invalid arguments for a new event.";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newEvent_blankName_exceptionThrown() {
        Exception e = Assertions.assertThrows(AlfredException.class, () -> {
            Event.createNewEvent(" /at 2020-12-12");
        });

        String expected = "Event name cannot be blank!";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newEvent_blankTime_exceptionThrown() {
        Exception e = Assertions.assertThrows(AlfredException.class, () -> {
            Event.createNewEvent("name /at  ");
        });

        String expected = "Event time cannot be blank!";
        assertTrue(e.getMessage().contains(expected));
    }

    @Test
    public void newEvent_invalidDateTimeFormat_exceptionThrown() {
        Exception e = Assertions.assertThrows(AlfredException.class, () -> {
            Event.createNewEvent("name /at 2020-13-13");
        });

        String expected = "DateTime format is invalid.";
        assertTrue(e.getMessage().contains(expected));
    }
}
