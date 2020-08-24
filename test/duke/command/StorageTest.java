package duke.command;

import duke.DukeDateTime;
import org.junit.jupiter.api.Test;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    void testSaveAndLoad() {

        // Save taskList to file
        List<Task> originalTaskList = new ArrayList<>(3);
        Task task1 = new ToDo("task1");
        Task task2 = new Deadline("task2", new DukeDateTime());
        Task task3 = new Event("task3", new DukeDateTime(), new DukeDateTime());

        originalTaskList.add(task1);
        originalTaskList.add(task2);
        originalTaskList.add(task3);

        new SaveCommand(originalTaskList, "save.txt").execute();

        // Load taskList from file
        List<Task> recoveredTaskList = new ArrayList<>(3);
        new LoadCommand(recoveredTaskList, "save.txt").execute();

        // Check loaded taskList is similar to original
        assertEquals(originalTaskList, recoveredTaskList);
        assertEquals(task1, recoveredTaskList.get(0));
        assertEquals(task2, recoveredTaskList.get(1));
        assertEquals(task3, recoveredTaskList.get(2));

    }
}
