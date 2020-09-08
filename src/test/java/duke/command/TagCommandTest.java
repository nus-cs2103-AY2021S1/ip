package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

class TagCommandTest {

    @Test
    void execute_validCommand_tagTaskFromTaskList() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("hello"));
        Command cmd1 = new TagCommand("tag 1 balls");
        Command cmd2 = new TagCommand("tag 1 dogs");
        cmd1.execute(tasks);
        cmd2.execute(tasks);
        assertEquals(tasks.get(1).toString(), "[T][ ] hello (tags: balls, dogs)");
    }

    @Test
    void execute_invalidIndex_throwsException() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("hello"));
        Command cmd1 = new TagCommand("tag 0 ");
        assertThrows(DukeException.class, () ->
            cmd1.execute(tasks)
        );

        Command cmd2 = new TagCommand("tag 3");
        assertThrows(DukeException.class, () ->
            cmd2.execute(tasks)
        );
    }
}
