import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testDateNoTime() {
        assertEquals("[D][✘] birthday (by: Dec 12 2020)",
                new Deadline("birthday",  "2020-12-12", "").toString());
    }

    @Test
    public void testDateWithTime() {
        assertEquals("[D][✘] birthday (by: Dec 12 2020 12:12)",
                new Deadline("birthday",  "2020-12-12", "12:12").toString());
    }

}
