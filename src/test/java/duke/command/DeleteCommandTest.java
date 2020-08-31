package duke.command;

import duke.util.DukeDateTime;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommandTest {

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

        ReversibleCommand c1 = new DeleteCommand(taskList, task1);
        ReversibleCommand c2 = new DeleteCommand(taskList, task2);
        ReversibleCommand c3 = new DeleteCommand(taskList, task3);

        // Actual test
        // Execute 3 DeleteCommands
        c1.execute();
        c2.execute();
        c3.execute();

        // Ensure 3 items are removed from taskList
        assertEquals(0, taskList.size());

        // Undo the 3 DeleteCommands
        c1.reverse();
        c2.reverse();
        c3.reverse();

        // Ensure there are 3 items in taskList
        assertEquals(3, taskList.size());
    }

}
