package task;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void DeadlineConstructor_DeadlineWithName(){
        assertEquals("[D][\u2718] do homework (by: Oct 19 2020)",
                new Deadline("do homework", "2020-10-19").toString());
    }

}
