package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("Drink Water"));

        String deadlineDateTime = "2020-09-10 2320";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime localDateTime = LocalDateTime.parse(deadlineDateTime, formatter);
        tasks.addTask(new Event("Meeting", localDateTime));
        assertEquals(tasks.getSize(), 2);
    }

    @Test
    public void deleteTaskTest() {
        TaskList tasks = new TaskList();
        try {
            tasks.deleteTask(-1);
        } catch (DukeException e) {
            assertEquals("Sorry, the task does not exist", e.getMessage());
        }
    }
}
