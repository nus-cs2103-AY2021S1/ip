package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void saveFormat_taskNotDone_zeroPrinted() {
        Deadline deadline = new Deadline("test1", "test1");
        assertEquals("D | 0 | 0 | test1 | test1", deadline.getSaveFormat());
    }

    @Test
    public void saveFormat_taskDone_onePrinted() {
        Deadline deadline = new Deadline("test1", "test1");
        deadline.setDone();
        assertEquals("D | 1 | 0 | test1 | test1", deadline.getSaveFormat());
    }

    @Test
    public void deadlineDisplay_taskNotDone_crossPrinted() {
        Deadline deadline = new Deadline("test1", "test1");
        assertEquals("[D][" + "\u2718" + "] test1 (by: test1)", deadline.toString());
    }

    @Test
    public void deadlineDisplay_taskDone_tickPrinted() {
        Deadline deadline = new Deadline("test1", "test1");
        deadline.setDone();
        assertEquals("[D][" + "\u2713" + "] test1 (by: test1)", deadline.toString());
    }

    @Test
    public void saveFormat_taskDoneNoReminder_oneZeroPrinted() {
        Deadline deadline = new Deadline("test1", "test1");
        deadline.setDone();
        assertEquals("D | 1 | 0 | test1 | test1", deadline.getSaveFormat());
    }

    @Test
    public void saveFormat_taskDoneHasReminder_oneOnePrinted() {
        Deadline deadline = new Deadline("test1", "test1");
        deadline.setDone();
        deadline.setReminder();
        assertEquals("D | 1 | 1 | test1 | test1", deadline.getSaveFormat());
    }

    @Test
    public void saveFormat_taskNotDoneHasReminder_zeroOnePrinted() {
        Deadline deadline = new Deadline("test1", "test1");
        deadline.setReminder();
        assertEquals("D | 0 | 1 | test1 | test1", deadline.getSaveFormat());
    }

    @Test
    public void saveFormat_taskNotDoneNoReminder_zeroZeroPrinted() {
        Deadline deadline = new Deadline("test1", "test1");
        assertEquals("D | 0 | 0 | test1 | test1", deadline.getSaveFormat());
    }
}
