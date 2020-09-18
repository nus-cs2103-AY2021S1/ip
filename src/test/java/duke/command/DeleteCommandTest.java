package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.ToDo;

/**
 * Tests for the deletion of commands.
 */
public class DeleteCommandTest extends CommandTests {

    /**
     * Tests deletion of tasks command.
     */
    @Test
    public void execute_DeleteCommand() {
        try {
            ToDo toDo1 = new ToDo("test1");
            ToDo toDo2 = new ToDo("test2");
            ToDo toDo3 = new ToDo("test3");
            SimpleCommand sc1 = new DeleteCommand("2");
            taskList.add(toDo1);
            taskList.add(toDo2);
            taskList.add(toDo3);
            // Tests
            assertEquals(ui.deleteTask(toDo2, 2), sc1.execute(taskList, ui, storage));
            assertEquals(2, storage.getTasks().size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
