package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void newDeadlineTest() {
        try {
            Deadline d = new Deadline("Assignment 1", "2020/09/01");
            fail();
        } catch (Exception e) {
            assertEquals("Invalid input date, please input as yyyy-mm-dd.", e.getMessage());
        }
    }


}
