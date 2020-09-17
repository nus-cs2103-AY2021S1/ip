package duke.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidIndexException;

public class TaskListTest {
    private TaskList t = new TaskList();

    @Test
    public void completeTask_invalidIndex_invalidIndexException() {
        try {
            t.completeTask(-1);
        } catch (InvalidIndexException e) {
            assertEquals("OOPS! Command was not executed! Please choose an index that is within range.",
                    e.getMessage());
        }
    }

    @Test
    public void display_emptyTaskList_noTaskSFound() {
        String result = t.display(new ArrayList<>());
        assertEquals(Ui.EMPTY_LIST_MESSAGE, result);
    }
}
