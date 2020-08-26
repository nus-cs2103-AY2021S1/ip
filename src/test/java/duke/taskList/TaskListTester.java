package duke.taskList;

import duke.TaskList.TaskList;
import duke.Tasks.ToDo;
import duke.Tasks.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTester {
    @Test
    @DisplayName("Testing addTask and getSize method in TaskList class")
    public void testGetDeadlineDate() {
        TaskList taskList = new TaskList();
        Task testTask = new ToDo("Do Something");
        taskList.addTask(testTask);
        int result = taskList.getSize();
        assertEquals(result, 1);
    }

    @Test
    @DisplayName("Testing removeTask method in Deadline class")
    public void testToString() {
        TaskList taskList = new TaskList();
        Task testTask = new ToDo("Do Something");
        taskList.addTask(testTask);
        taskList.removeTask(0);
        int result = taskList.getSize();
        assertEquals(result, 0);
    }

}
