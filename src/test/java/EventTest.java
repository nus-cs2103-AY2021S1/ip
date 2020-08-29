import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][✘] attend ninja fruit competition (at: Oct 10 2020)",
                new Event("attend ninja fruit competition ", "2020-10-10").toString());
    }
}
