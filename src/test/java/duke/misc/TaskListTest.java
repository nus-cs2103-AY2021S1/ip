package duke.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidIndexException;

public class TaskListTest {
    private TaskList t = new TaskList();

    @Test
    public void invalidIndexTest() {
        try {
            t.completeTask(-1);
        } catch (InvalidIndexException e) {
            assertEquals("OOPS!!! The index you have chosen is out of bounds",
                    e.getMessage());
        }
    }
}
