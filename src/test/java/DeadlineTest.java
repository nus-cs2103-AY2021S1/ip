import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Deadline;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        Deadline d = new Deadline("submission", "2020-05-02", false, 0);
        assertEquals("[D][\u2718] submission (by: May 2 2020)", d.toString());
    }
}
