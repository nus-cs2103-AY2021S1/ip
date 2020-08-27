package duke;

import duke.component.Ui;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void tasksLeft_0_noneFoundMessage() {
        Ui ui = new Ui();
        assertEquals("No tasks found. Add a task now!", ui.taskLeftMessage(0));
    }

    @Test
    public void getStatusIcon_null_appropriateMessage() {
        Ui ui = new Ui();
        Todo task = new Todo("save the world");
        assertEquals("[✘] ", ui.getStatusIcon(task));

        task.markDone();
        assertEquals("[✓] ", ui.getStatusIcon(task));
    }


}
