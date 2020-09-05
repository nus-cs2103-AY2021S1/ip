package duke.core.command;

import duke.designpattern.command.ReversibleExecutable;
import duke.core.util.DukeDateTime;
import duke.core.task.Deadline;
import duke.core.task.Event;
import duke.core.task.Task;
import duke.core.task.ToDo;
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

        ReversibleExecutable c1 = new DeleteCommand(taskList, task1);
        ReversibleExecutable c2 = new DeleteCommand(taskList, task2);
        ReversibleExecutable c3 = new DeleteCommand(taskList, task3);

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
