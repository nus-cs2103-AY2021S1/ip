package command;

import misc.DukeDateTime;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCommandTest {

    @Test
    void isModifying_true() {
        assertTrue(new DeleteCommand(null, null).hasUndo());
    }

    @Test
    void isExit_false() {
        assertFalse(new DeleteCommand(null, null).isExit());
    }
    @Test
    void executeAndUndo() {
        DukeDateTime dateTime = new DukeDateTime(LocalDateTime.now().format(DukeDateTime.FORMAT));
        List<Task> taskList = new ArrayList<>(5);

        Task task1 = new ToDo("Todo 1");
        Task task2 = new Deadline("Deadline 1", dateTime);
        Task task3 = new Event("Event 1", dateTime, dateTime);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        // Pre-test
        assertEquals(3, taskList.size());

        Command c1 = new DeleteCommand(taskList, task1);
        Command c2 = new DeleteCommand(taskList, task2);
        Command c3 = new DeleteCommand(taskList, task3);

        // Actual test
        c1.execute();
        c2.execute();
        c3.execute();
        assertEquals(0, taskList.size());

        c1.undo();
        c2.undo();
        c3.undo();
        assertEquals(3, taskList.size());
    }

}