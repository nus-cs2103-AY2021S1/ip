package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void testToData() {
        assertEquals("D~0~tP AB3 familiarization~2020-08-26 2359\n",
                new Deadline("tP AB3 familiarization", "2020-08-26 2359").toData());
    }
}
