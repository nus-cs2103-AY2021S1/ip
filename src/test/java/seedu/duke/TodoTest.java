package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void constructor_validCreation_success() {
        assertEquals("[T][✘] Dinner", new Todo("Dinner").toString());
    }

    @Test
    public void constructor_validTask_failure() {
        assertEquals("Dinner", new Todo("Dinner").getTask());
    }
}
