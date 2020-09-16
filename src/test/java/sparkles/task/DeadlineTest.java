package sparkles.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlineTest {

    @Test
    public void test() {
        assertEquals("Jan 15 2020 06:00 PM", new Deadline("", "2020-01-15 18:00").printDateNTime());

        assertEquals("Oct 28 2021 03:00 AM", new Deadline("", "2021-10-28 03:00").printDateNTime());
    }
}
