package duke.core.command;

import duke.designpattern.command.ReversibleExecutable;
import duke.core.task.Deadline;
import duke.core.task.Event;
import duke.core.task.Task;
import duke.core.task.ToDo;
import duke.core.util.DukeDateTime;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoneCommandTest {

    @Test
    void executeAndReverse_success() {
        DukeDateTime dateTime = new DukeDateTime();
        List<Task> taskList = new ArrayList<>(5);

        Task task1 = new ToDo("Todo 1");
        Task task2 = new Deadline("Deadline 1", dateTime);
        Task task3 = new Event("Event 1", dateTime, dateTime);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        // Pre-test, ensure there are 3 items in taskList
        assertEquals(3, taskList.size());

        // Pre-test, ensure items are not completed
        assertFalse(taskList.get(0).isCompleted());
        assertFalse(taskList.get(1).isCompleted());
        assertFalse(taskList.get(2).isCompleted());

        ReversibleExecutable c1 = new DoneCommand(task1);
        ReversibleExecutable c2 = new DoneCommand(task2);
        ReversibleExecutable c3 = new DoneCommand(task3);

        // Actual test
        // Execute 3 DoneCommands
        c1.execute();
        c2.execute();
        c3.execute();

        // Ensure Tasks are marked done
        assertTrue(taskList.get(0).isCompleted());
        assertTrue(taskList.get(1).isCompleted());
        assertTrue(taskList.get(2).isCompleted());

        // Undo the DoneCommand
        c1.reverse();
        c2.reverse();
        c3.reverse();

        // Ensure Tasks are marked as not done
        assertFalse(taskList.get(0).isCompleted());
        assertFalse(taskList.get(1).isCompleted());
        assertFalse(taskList.get(2).isCompleted());
    }

}
