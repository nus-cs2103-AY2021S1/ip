import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Deadline;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][\u2718] must eat the fruit (by: Oct 10 2020)",
                new Deadline("must eat the fruit ", "2020-10-10").toString());
    }
}
