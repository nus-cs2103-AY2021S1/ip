package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private static final String TICK_ICON = "\u2713";
    private static final String CROSS_ICON = "\u2718";

    @Test
    public void undoneDeadlineToString_taskDescription_stringPresentation() {
        Task test = new Deadline("test task", "5 o'clock");
        String expected = "[D] [" + CROSS_ICON + "] test task (by: 5 o'clock)";
        String actual = test.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void doneTaskToString_taskDescription_stringPresentation() {
        Task test = new Deadline("test task", "5 o'clock");
        String expected = "[D] [" + TICK_ICON + "] test task (by: 5 o'clock)";
        String actual = test.markAsDone().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void doneTaskToString_validDateInput1_stringPresentation() {
        Task test = new Deadline("test task", "20/04/2019");
        String expected = "[D] [" + CROSS_ICON + "] test task (by: 20 Apr 2019)";
        String actual = test.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void doneTaskToString_validDateInput2_stringPresentation() {
        Task test = new Deadline("test task", "20/4/2019");
        String expected = "[D] [" + CROSS_ICON + "] test task (by: 20 Apr 2019)";
        String actual = test.toString();
        assertEquals(expected, actual);
    }
}
