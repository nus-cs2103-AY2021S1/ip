package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void Test() {
        String dateTime1 = "2020/08/27 2359";
        String dateTime2;
        Deadline deadline = new Deadline("CS2103", false, dateTime1);
        try {
            deadline.updateDateTime();
        } catch (DukeException e) {
            e.printStackTrace();
        }
        dateTime2 = deadline.deadline;
        assertEquals(dateTime1, dateTime2);
    }
}
