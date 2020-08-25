package duke.task;

import duke.dukeException.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void eventConstructorTest_wrongDateTimeFormat_exceptionThrown() {
        try {
            new Event("Concert", "020720 18:00");
        } catch (DukeException e) {
            assertEquals("Yo! DateTime format is wrong. <dd/MM/yy [HH:MM]>", e.getMessage());
        }
    }

    @Test
    public void eventConstructorTest_correctDateTimeFormat_success() {
        try {
            Event e = new Event("Concert", "02/07/20 18:00");
            assertEquals(new Event("Concert", "02/07/20 18:00").toString(), e.toString());
        } catch (DukeException e) {
            fail();
        }
    }

}
