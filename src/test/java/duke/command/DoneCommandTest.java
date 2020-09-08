package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DoneCommandTest {

    @Test
    void execute_validCommand_setTaskAsDone() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("hello"));
        Command cmd = new DoneCommand("done 1");
        assertEquals(tasks.get(1).toString(), "[T][ ] hello");
        cmd.execute(tasks);
        assertEquals(tasks.get(1).toString(), "[T][x] hello");
    }

    @Test
    void execute_invalidIndex_throwsException() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("hello"));
        Command cmd1 = new DeleteCommand("done 0");
        assertThrows(DukeException.class, () ->
                cmd1.execute(tasks)
        );

        Command cmd2 = new DeleteCommand("done 3");
        assertThrows(DukeException.class, () ->
                cmd2.execute(tasks)
        );
    }
}