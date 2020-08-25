import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testDateNoTime() {
        assertEquals("[E][✘] birthday (at: Dec 12 2020)",
                new Event("birthday",  "2020-12-12", "").toString());
    }

    @Test
    public void testDateWithTime() {
        assertEquals("[E][✘] birthday (at: Dec 12 2020 12:12)",
                new Event("birthday",  "2020-12-12", "12:12").toString());
    }

}
