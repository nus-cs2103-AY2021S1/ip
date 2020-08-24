package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCommandTest {

    @Test
    void execute_validCommand_deleteTaskFromTaskList() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("hello"));
        assertEquals(tasks.size(), 1);
        Command cmd = new DeleteCommand("delete 1");
        cmd.execute(tasks);
        assertEquals(tasks.size(), 0);
    }

    @Test
    void execute_invalidIndex_throwsException() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("hello"));
        Command cmd1 = new DeleteCommand("delete 0");
        assertThrows(DukeException.class, () ->
                cmd1.execute(tasks)
        );

        Command cmd2 = new DeleteCommand("delete 3");
        assertThrows(DukeException.class, () ->
                cmd2.execute(tasks)
        );
    }
}