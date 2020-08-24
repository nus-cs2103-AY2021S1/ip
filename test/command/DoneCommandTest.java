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

class DoneCommandTest {

    @Test
    void isModifying_true() {
        assertTrue(new DoneCommand(null).hasUndo());
    }

    @Test
    void isExit_false() {
        assertFalse(new DoneCommand(null).isExit());
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

        assertFalse(taskList.get(0).isCompleted());
        assertFalse(taskList.get(1).isCompleted());
        assertFalse(taskList.get(2).isCompleted());

        // Actual test
        Command c1 = new DoneCommand(task1);
        Command c2 = new DoneCommand(task2);
        Command c3 = new DoneCommand(task3);

        c1.execute();
        c2.execute();
        c3.execute();

        assertTrue(taskList.get(0).isCompleted());
        assertTrue(taskList.get(1).isCompleted());
        assertTrue(taskList.get(2).isCompleted());

        c1.undo();
        c2.undo();
        c3.undo();

        assertFalse(taskList.get(0).isCompleted());
        assertFalse(taskList.get(1).isCompleted());
        assertFalse(taskList.get(2).isCompleted());


    }

}