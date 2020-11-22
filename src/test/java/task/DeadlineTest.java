package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructionTest() {
        Deadline deadline1 = new Deadline("deadline1", "2020-08-23", "");
        assertEquals(deadline1.toString(), "[D][\u2717] deadline1 (by: Aug 23 2020)");
        Deadline deadline2 = new Deadline("deadline2", "2020-09-24", "18:00");
        assertEquals(deadline2.toString(), "[D][\u2717] deadline2 (by: Sep 24 2020 18:00)");
    }
}
