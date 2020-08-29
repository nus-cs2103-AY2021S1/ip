package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTest {
    @Test
    public void eventCreation_normalInput_success() {
        Event event = new Event("read book", LocalDateTime.of(2020,02,14,15,50));
        Assertions.assertEquals("[E][\u2718] read book (at: Feb 14 2020 1550)", event.toString());
    }

    @Test
    public void eventCreation_noDesc_exceptionThrown() {
        try {
            Event event = new Event("", LocalDateTime.now());
            Assertions.fail();
        } catch (DukeException de) {
            Assertions.assertEquals(
                    new DukeException("The description or date of \"event\" cannot be empty").getMessage(),
                    de.getMessage());
        }
    }

    @Test
    public void eventCreation_dateTimeIsNull_exceptionThrown() {
        try {
            Event event = new Event("Lunch", null);
            Assertions.fail();
        } catch (DukeException de) {
            Assertions.assertEquals(
                    new DukeException("The description or date of \"event\" cannot be empty").getMessage(),
                    de.getMessage());
        }
    }

    @Test
    public void eventDone_setDone_success() {
        Event event = new Event("read book", LocalDateTime.parse("2019-09-11T13:40"));
        event.setDone();
        Assertions.assertEquals("[E][\u2713] read book (at: Sep 11 2019 1340)", event.toString());
    }

    @Test
    public void eventExport_noInput_success() {
        Event event = new Event("wash clothes",
                LocalDateTime.parse("10/11/2018 0800", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
        event.setDone();
        Assertions.assertEquals("E`1`wash clothes`10/11/2018 0800", event.getSaveToFileString());
    }
}
