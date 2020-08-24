package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoCommandTest {
    @Test
    void execute_validCommand_addToDoTaskToTaskList() throws DukeException {
        TaskList tasks = new TaskList();
        Command cmd = new ToDoCommand("todo return book");
        cmd.execute(tasks);
        assertEquals(tasks.get(1).toString(), "[T][ ] return book");
    }

    @Test
    void execute_invalidCommandNoDescription_throwsException() {
        TaskList tasks = new TaskList();
        Command cmd1 = new ToDoCommand("todo ");
        assertThrows(DukeException.class, () ->
                cmd1.execute(tasks)
        );

        Command cmd2 = new ToDoCommand("todo");
        assertThrows(DukeException.class, () ->
                cmd2.execute(tasks)
        );
    }
}