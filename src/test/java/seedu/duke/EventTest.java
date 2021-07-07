package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void constructor_validCreation_success() {
        assertEquals("[E][X] Dinner (at: Aug 24 2020)", new Event("Dinner", "2020-08-24").toString());
    }

    @Test
    public void storageString_validString_success() {
        assertEquals("E ~ 0 ~ Dinner ~ Aug 24 2020",
                new Event("Dinner", "2020-08-24").getStorageString("E", "Aug 24 2020"));
    }
}
