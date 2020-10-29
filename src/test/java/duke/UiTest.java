package duke;

import duke.component.CliUi;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void tasksLeft_0_noneFoundMessage() {
        CliUi ui = new CliUi();
        assertEquals("No tasks found. Add a task now!", ui.taskLeftMessage(0));
    }

    @Test
    public void getStatusIcon_null_appropriateMessage() {
        CliUi ui = new CliUi();
        Todo task = new Todo("save the world");
        assertEquals("[✘] ", ui.getStatusIcon(task));

        task.markDone();
        assertEquals("[✓] ", ui.getStatusIcon(task));
    }


}
