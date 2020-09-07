package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void saveFormat_taskNotDone_zeroPrinted() {
        Event event = new Event("test1", "test1");
        assertEquals("E | 0 | 0 | test1 | test1", event.getSaveFormat());
    }

    @Test
    public void saveFormat_taskDone_onePrinted() {
        Event event = new Event("test1", "test1");
        event.setDone();
        assertEquals("E | 1 | 0 | test1 | test1", event.getSaveFormat());
    }

    @Test
    public void eventDisplay_taskNotDone_crossPrinted() {
        Event event = new Event("test1", "test1");
        assertEquals("[E][" + "\u2718" + "] test1 (at: test1)", event.toString());
    }

    @Test
    public void eventDisplay_taskDone_tickPrinted() {
        Event event = new Event("test1", "test1");
        event.setDone();
        assertEquals("[E][" + "\u2713" + "] test1 (at: test1)", event.toString());
    }

    @Test
    public void saveFormat_taskDoneNoReminder_oneZeroPrinted() {
        Event event = new Event("test1", "test1");
        event.setDone();
        assertEquals("E | 1 | 0 | test1 | test1", event.getSaveFormat());
    }

    @Test
    public void saveFormat_taskNotDoneNoReminder_zeroZeroPrinted() {
        Event event = new Event("test1", "test1");
        assertEquals("E | 0 | 0 | test1 | test1", event.getSaveFormat());
    }

    @Test
    public void saveFormat_taskDoneHasReminder_oneOnePrinted() {
        Event event = new Event("test1", "test1");
        event.setDone();
        event.setReminder();
        assertEquals("E | 1 | 1 | test1 | test1", event.getSaveFormat());
    }

    @Test
    public void saveFormat_taskNotDoneHasReminder_zeroOnePrinted() {
        Event event = new Event("test1", "test1");
        event.setReminder();
        assertEquals("E | 0 | 1 | test1 | test1", event.getSaveFormat());
    }
}
