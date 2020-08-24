package duke;

import duke.tasks.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {

    @Test
    public void createEvent_wrongDateTimeFormat_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            Event.createEvent("travel to japan", "2020-13-12 1800-2400");
        });

        String expected = "Rawr! Dino could not add your task. Make sure your format is correct.\n" +
                "Formats to input a task can be found by entering 'format'.";
        assertTrue(e.getMessage().contains(expected));
    }
}
