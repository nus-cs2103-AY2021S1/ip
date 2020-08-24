package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {

    @Test
    public void createEvent_wrongDateTimeFormat_exceptionThrown() {
        String errorMessage = "Rawr! Dino could not add your task. Make sure your format is correct.\n" +
                "Formats to input a task can be found by entering 'format'.";

        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            Event.createEvent("travel to japan", "2020-13-12 1800-2400");
        });
        assertEquals(errorMessage, e.getMessage());

        Exception ex = Assertions.assertThrows(DukeException.class, () -> {
            Event.createEvent("travel to south", "2020-12-121800-2200");
        });
        assertEquals(errorMessage, ex.getMessage());

        Exception exception = Assertions.assertThrows(DukeException.class, () -> {
            Deadline.createDeadline("travel to east", "wrong date /at time format");
        });
        assertTrue(exception.getMessage().contains(errorMessage));
    }

    @Test
    public void createDeadline_wrongDateTimeFormat_exceptionThrown() {
        String errorMessage = "Rawr! Dino could not add your task. Make sure your format is correct.\n" +
                "Formats to input a task can be found by entering 'format'.";

        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            Deadline.createDeadline("travel to japan", "2020-13-12 1800-2400");
        });
        assertTrue(e.getMessage().contains(errorMessage));

        Exception ex = Assertions.assertThrows(DukeException.class, () -> {
            Deadline.createDeadline("travel to japan", "wrong");
        });
        assertTrue(ex.getMessage().contains(errorMessage));

        Exception exception = Assertions.assertThrows(DukeException.class, () -> {
            Deadline.createDeadline("travel to japan", "wrong date /by time format");
        });
        assertTrue(exception.getMessage().contains(errorMessage));
    }

}
