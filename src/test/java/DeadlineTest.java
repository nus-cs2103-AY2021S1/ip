import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testGetBy() {
        assertEquals("2021", new Deadline("sleep", "2021").getBy());
    }
}
