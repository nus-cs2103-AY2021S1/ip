package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void stringConversion_withTime() throws DukeException {
        Deadline deadline = new Deadline("Test", "01/01/2020 0700");
        assertEquals("[D][✘][4] Test (by: Wed, 1 January 2020, 7:00AM)", deadline.toString());
    }

    @Test
    public void stringConversion_withoutTime() throws DukeException {
        Deadline deadline = new Deadline("Test", "01/01/2020");
        assertEquals("[D][✘][4] Test (by: Wed, 1 January 2020)", deadline.toString());
    }

    @Test
    void serializeTest_withoutTime() throws DukeException {
        Deadline deadline = new Deadline("Test", "01/01/2020");
        assertEquals("D | 0 | 4 | Test | 01/01/2020", deadline.serialize());
    }

    @Test
    void serializeTest_withTime() throws DukeException {
        Deadline deadline = new Deadline("Test", "01/01/2020 0700");
        assertEquals("D | 0 | 4 | Test | 01/01/2020 0700", deadline.serialize());
    }
}
