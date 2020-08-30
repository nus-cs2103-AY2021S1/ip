package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void createNewTaskTest() {
        Task newTask = Task.newTask("Exercise");
        assertEquals(newTask.getTaskName(), "Exercise", "Task name is incorrect");
        assertEquals(newTask.getIsCompleted(), false, "Task completion status is incorrect");
    }

    @Test
    public void createExistingTaskTest() {
        Task existingTask = Task.existingTask("Sleep", true);
        assertEquals(existingTask.getTaskName(), "Sleep", "Task name is incorrect");
        assertEquals(existingTask.getIsCompleted(), true, "Task completion status is incorrect");
    }

    @Test
    public void markAsDoneTest() {
        Task newTask = Task.newTask("Homework").markAsDone();
        assertEquals(newTask.getIsCompleted(), true,
                "Task completion status is incorrect after marking as done");
    }
}
