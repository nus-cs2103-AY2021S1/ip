package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;

/**
 * Tests the show list command.
 */
public class ShowCommandTest extends CommandTests {

    private final ShowCommand sc = new ShowCommand();

    /**
     * Tests of empty list display.
     */
    @Test
    public void execute_EmptyList() {
        assertEquals(ui.emptyTaskList(), sc.execute(taskList, ui, storage));
    }

    /**
     * Tests of tasklist displays.
     */
    @Test
    public void execute_ActualDisplay() {
        ToDo toDo = new ToDo("te");
        taskList.add(toDo);
        assertEquals(ui.showTaskList(taskList, ""), sc.execute(taskList, ui, storage));
    }
}
