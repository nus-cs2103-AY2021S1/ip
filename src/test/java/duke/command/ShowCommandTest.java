package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;

public class ShowCommandTest extends CommandTests {
    @Test
    public void test() {
        ShowCommand sc = new ShowCommand();
        assertEquals(ui.emptyTaskList(), sc.execute(taskList, ui, storage));
        ToDo toDo = new ToDo("te");
        taskList.add(toDo);
        assertEquals(ui.showTaskList(taskList, ""), sc.execute(taskList, ui, storage));
    }
}
