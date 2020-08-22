package duke.Task;

import duke.Exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void stringConversion_withTime() {
        Deadline deadline = new Deadline("Test", "01/01/2020 0700");
        assertEquals("[D][✘] Test (by: Wed, 1 January 2020, 7:00am)", deadline.toString());
    }

    @Test
    public void stringConversion_withoutTime() {
        Deadline deadline = new Deadline("Test", "01/01/2020");
        assertEquals("[D][✘] Test (by: Wed, 1 January 2020)", deadline.toString());
    }

    @Test
    void serializeTest_withoutTime() {
        Deadline deadline = new Deadline("Test", "01/01/2020");
        assertEquals("D | 0 | Test | 01/01/2020", deadline.serialize());
    }

    @Test
    void serializeTest_withTime() {
        Deadline deadline = new Deadline("Test", "01/01/2020 0700");
        assertEquals("D | 0 | Test | 01/01/2020 0700", deadline.serialize());
    }
}
