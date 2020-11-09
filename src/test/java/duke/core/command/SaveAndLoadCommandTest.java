package duke.core.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.core.DukeData;
import duke.core.task.Deadline;
import duke.core.task.Event;
import duke.core.task.Task;
import duke.core.task.ToDo;
import duke.core.util.DukeDateTime;

public class SaveAndLoadCommandTest {

    @Test
    void saveAndLoad_success() {

        // Create a list and populate with data
        List<Task> originalTaskList = new ArrayList<>(3);
        Task task1 = new ToDo("task1");
        Task task2 = new Deadline("task2", new DukeDateTime());
        Task task3 = new Event("task3", new DukeDateTime(), new DukeDateTime());
        originalTaskList.add(task1);
        originalTaskList.add(task2);
        originalTaskList.add(task3);

        // Save taskList to file
        new SaveCommand(originalTaskList, "save.txt").execute();

        // Load taskList from file
        List<Task> recoveredTaskList = new ArrayList<>(3);
        new LoadCommand(new DukeData(recoveredTaskList), "save.txt").execute();

        // Check loaded taskList is similar to original
        assertEquals(originalTaskList, recoveredTaskList);
        assertEquals(task1, recoveredTaskList.get(0));
        assertEquals(task2, recoveredTaskList.get(1));
        assertEquals(task3, recoveredTaskList.get(2));

    }
}
