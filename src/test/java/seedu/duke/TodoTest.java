package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toDoCreation_StringInput_Success() {
        assertEquals("[T][✘] Dinner", new Todo("Dinner").toString());
    }

    @Test
    public void toDoCreation_getTask_Failure() {
        assertEquals( "Dinner", new Todo("Dinner").getTask());
    }
}
