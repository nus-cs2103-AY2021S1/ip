package duke.command;

import duke.task.Task;
import duke.task.ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    private static final String TICK_ICON = "\u2713";
    private static final String CROSS_ICON = "\u2718";
    
    @Test
    public void undoneTaskToString_taskDescription_stringPresentation() {
        Task test = new ToDo("test task");
        String expected = "[T] [" + CROSS_ICON + "] test task";
        String actual = test.toString(); 
        assertEquals(expected, actual);
    }
    
    @Test
    public void doneTaskToString_taskDescription_stringPresentation() {
        Task test = new ToDo("test task");
        String expected = "[T] [" + TICK_ICON + "] test task";
        String actual = test.markAsDone().toString();
        assertEquals(expected, actual);
    }
}
