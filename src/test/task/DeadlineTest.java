package task;

import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DeadlineTest {
    @Test
    void event_toArray_test(){
        Deadline deadline = new Deadline("project", "2020-10-10");
        String[] expected = new String[]{"D", "0", "project", "2020-10-10"};
        assertArrayEquals(expected, deadline.toArray());
    }
}
