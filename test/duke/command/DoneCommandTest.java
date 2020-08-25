package duke.command;

import duke.DukeDateTime;
import org.junit.jupiter.api.Test;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoneCommandTest {

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
        ReversibleCommand c1 = new DoneCommand(task1);
        ReversibleCommand c2 = new DoneCommand(task2);
        ReversibleCommand c3 = new DoneCommand(task3);

        c1.execute();
        c2.execute();
        c3.execute();

        assertTrue(taskList.get(0).isCompleted());
        assertTrue(taskList.get(1).isCompleted());
        assertTrue(taskList.get(2).isCompleted());

        c1.reverse();
        c2.reverse();
        c3.reverse();

        assertFalse(taskList.get(0).isCompleted());
        assertFalse(taskList.get(1).isCompleted());
        assertFalse(taskList.get(2).isCompleted());
    }

}