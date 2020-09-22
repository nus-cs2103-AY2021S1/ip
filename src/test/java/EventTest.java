import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void testSaveDataStringConversion() {
        try {
            Deadline deadline = Deadline.createDeadline("Deadline 1", "2020-08-01");
            assertEquals("0 D Deadline 1\n2020-08-01", deadline.getSaveDataString());
        } catch (DukeException ex) {
            fail();
        }
    }
}
