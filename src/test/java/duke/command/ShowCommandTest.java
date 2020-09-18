package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.ToDo;

/**
 * Tests the show list command.
 */
public class ShowCommandTest extends CommandTests {

    private final ShowCommand sc = new ShowCommand();

    /**
     * Tests for empty list display.
     */
    @Test
    public void execute_emptyList_success() {
        try {
            assertEquals(ui.emptyTaskList(), executeTask(sc));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    /**
     * Tests of tasklist displays.
     */
    @Test
    public void execute_display_success() {
        try {
            ToDo toDo = new ToDo("te");
            taskList.add(toDo);
            assertEquals(ui.showTaskList(taskList, ""), executeTask(sc));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

}
