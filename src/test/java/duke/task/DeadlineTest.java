package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void constructor_invalidDate_throwException() {
        try {
            Deadline d = new Deadline("UTC2107 Consultation", "2020/08/25");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! Invalid date detected! Please enter date as yyyy-mm-dd.", e.getMessage());
        }
    }

    @Test
    public void toStorageStringTest() {
        try {
            Deadline deadline = new Deadline("UTC2107 Consultation", "2020-08-25");
            assertEquals("D | 0 | UTC2107 Consultation | 2020-08-25", deadline.toStorageString());
            deadline.markAsDone();
            assertEquals("D | 1 | UTC2107 Consultation | 2020-08-25", deadline.toStorageString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toStringTest() {
        try {
            Deadline deadline = new Deadline("UTC2107 Consultation", "2020-08-25");
            assertEquals("[D][\u2718] UTC2107 Consultation (by: Aug 25 2020)", deadline.toString());

            deadline.markAsDone();
            assertEquals("[D][\u2713] UTC2107 Consultation (by: Aug 25 2020)", deadline.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
