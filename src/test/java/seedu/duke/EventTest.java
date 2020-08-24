package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventCreation_StringInput_Success() {
        assertEquals("[E][✘] Dinner (at: Aug 24 2020)", new Event("Dinner", "2020-08-24").toString());
    }

    @Test
    public void eventCreation_storageString_Success() {
        assertEquals( "D ~ 0 ~ Dinner ~ Aug 24 2020",
                new Event("Dinner", "2020-08-24").getStorageString("D", "Aug 24 2020"));
    }
}
