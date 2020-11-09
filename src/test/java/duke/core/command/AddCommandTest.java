package duke.core.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.core.task.Deadline;
import duke.core.task.Event;
import duke.core.task.Task;
import duke.core.task.ToDo;
import duke.core.util.DukeDateTime;
import duke.designpattern.command.ReversibleExecutable;

class AddCommandTest {

    @Test
    void executeAndReverse_success() {
        DukeDateTime dateTime = new DukeDateTime();
        List<Task> taskList = new ArrayList<>(5);

        Task task1 = new ToDo("Todo 1");
        Task task2 = new Deadline("Deadline 1", dateTime);
        Task task3 = new Event("Event 1", dateTime, dateTime);

        ReversibleExecutable c1 = new AddCommand(taskList, task1);
        ReversibleExecutable c2 = new AddCommand(taskList, task2);
        ReversibleExecutable c3 = new AddCommand(taskList, task3);

        // Execute 3 AddCommands
        c1.execute();
        c2.execute();
        c3.execute();

        // Ensure 3 items are added to list
        assertEquals(3, taskList.size());
        assertEquals(task1, taskList.get(0));
        assertEquals(task2, taskList.get(1));
        assertEquals(task3, taskList.get(2));

        // Undo 3 AddCommands
        c3.reverse();
        c2.reverse();
        c1.reverse();

        // Ensure 3 items are removed from list
        assertEquals(0, taskList.size());

    }
}
