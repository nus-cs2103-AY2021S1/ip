package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class EventTest {

    @Test
    public void createTask_success() throws DukeException {
        Event e = Event.createTask("EVENT meeting /at      2019/02/20 16:00-18:00");
        assertEquals("[E][✘] meeting (APPEAR at: 20 February 2019 4:00pm - 6:00pm)", e.toString());
    }

    @Test
    public void createTask_missingDescription_failure() {

        try {
            Event.createTask("EVENT  /at      2019/02/20 16:00-18:00");
            fail();
        } catch (DukeException e) {
            String err = " Oops!! You gonna forget what this is about if you"
                    + " dont give me a description... *woof*\n";
            assertEquals(err, e.getMessage());
        }

    }

    @Test
    public void createTask_missingDateTime_failure() {

        try {
            Event.createTask("EVENT meeting /at  ");
            fail();
        } catch (DukeException e) {
            String err = " Oops!! Are you planning to ghost the event?"
                    + " You didnt state the time of this event... *woof*\n";
            assertEquals(err, e.getMessage());
        }

    }

    @Test
    public void createTask_keyWord_failure() {

        try {
            Event.createTask("EVENT meeting  ");
            fail();
        } catch (DukeException e) {
            String err = " Oops!! You missed out some vital information/keyword... *woof*\n";
            assertEquals(err, e.getMessage());
        }

    }

    @Test
    public void createTask_wrongDateTimeFormat_failure() {

        try {
            Event.createTask("EVENT meeting /at 2019-02-20 1600-1800 ");
            fail();
        } catch (DukeException e) {
            String err = " Please input event time in the following format:\n"
                    + "    YYYY/MM/DD HH:MM-HH:MM!\n" + " *Woof woof*\n";
            assertEquals(err, e.getMessage());
        }

    }

    @Test
    public void task_checkFormatPrint() throws DukeException {
        Event e = Event.createTask("EVENT       meeting /at 2019/02/20    16:00-18:00 ");
        assertEquals("[E][✘] meeting (APPEAR at: 20 February 2019 4:00pm - 6:00pm)", e.toString());
    }

    @Test
    public void createTaskWithDoneTest() {
        Event e = new Event("meeting", "20 February 2019 16:00-18:00", true);
        assertEquals("[E][✓] meeting (APPEAR at: 20 February 2019 4:00pm - 6:00pm)", e.toString());
    }

    @Test
    public void compareDateTest_sameDate() throws DukeException {
        Event e = Event.createTask("EVENT meeting /at 2019/02/20 16:00-18:00 ");
        assertTrue(e.compareDate(LocalDate.parse("2019-02-20")));
    }

    @Test
    public void compareDateTest_diffDate() throws DukeException {
        Deadline d = Deadline.createTask("deadLine        project      /by  2019/02/20");
        assertFalse(d.compareDate(LocalDate.parse("2019-02-21")));
    }
}
