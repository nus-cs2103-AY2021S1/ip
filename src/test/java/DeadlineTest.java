import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Deadline;

public class DeadlineTest {
    @Test
    public void testGetBy() {
        assertEquals("2021", new Deadline("sleep", "2021").getBy());
    }
}
