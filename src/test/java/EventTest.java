import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Event;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][\u2718] attend ninja fruit competition (at: Oct 10 2020)",
                new Event("attend ninja fruit competition ", "2020-10-10").toString());
    }
}
