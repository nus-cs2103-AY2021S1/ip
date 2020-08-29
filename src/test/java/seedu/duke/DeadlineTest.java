package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void constructor_validCreation_success() {
        assertEquals("[D][✘] Dinner (by: Aug 24 2020)", new Deadline("Dinner", "2020-08-24").toString());
    }

    @Test
    public void storageString_validString_success() {
        assertEquals("D ~ 0 ~ Dinner ~ Aug 24 2020",
                new Deadline("Dinner", "2020-08-24").getStorageString("D", "Aug 24 2020"));
    }
}
