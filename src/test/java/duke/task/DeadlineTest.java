package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void deadlineConstructor_deadlineWithNameAndDate(){
        assertEquals("[D][\u2718] do homework (by: Oct 19 2020)",
                new Deadline("do homework", "2020-10-19").toString());
    }

}
