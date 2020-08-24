package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineCreation_StringInput_Success() {
        assertEquals("[D][✘] Dinner (by: Aug 24 2020)", new Deadline("Dinner", "2020-08-24").toString());
    }

    @Test
    public void deadlineCreation_storageString_Success() {
        assertEquals( "D ~ 0 ~ Dinner ~ Aug 24 2020",
                new Deadline("Dinner", "2020-08-24").getStorageString("D", "Aug 24 2020"));
    }
}
