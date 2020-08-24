package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestTask {

    @Test
    public void getDone_notDone_success() {
        assertFalse(new Task("description").getDone());
    }

    @Test
    public void getDescription_description_success() {
        assertEquals("hello", new Task("hello").getDescription());
    }
}
