package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void saveFormat_taskNotDone_0Printed() {
        Deadline deadline = new Deadline("test1", "test1");
        assertEquals("D | 0 | test1 | test1", deadline.getSaveFormat());
    }

    @Test
    public void saveFormat_taskDone_1Printed() {
        Deadline deadline = new Deadline("test1", "test1");
        deadline.setDone();
        assertEquals("D | 1 | test1 | test1", deadline.getSaveFormat());
    }

    @Test
    public void deadlineDisplay_taskNotDone_crossPrinted() {
        Deadline deadline = new Deadline("test1", "test1");
        assertEquals("[D][✗] test1 (by: test1)", deadline.toString());
    }

    @Test
    public void deadlineDisplay_taskDone_tickPrinted() {
        Deadline deadline = new Deadline("test1", "test1");
        deadline.setDone();
        assertEquals("[D][✓] test1 (by: test1)", deadline.toString());
    }
}
