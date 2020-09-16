package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void newTask_newTaskCreated() {
        Task newTask = Task.newTask("Exercise");
        assertEquals(newTask.getTaskName(), "Exercise", "Task name is incorrect");
        assertEquals(newTask.getCompletionStatus(), false, "Task completion status is incorrect");
    }

    @Test
    public void existingTask_existingTaskCreated() {
        Task existingTask = Task.existingTask("Sleep", true);
        assertEquals(existingTask.getTaskName(), "Sleep", "Task name is incorrect");
        assertEquals(existingTask.getCompletionStatus(), true, "Task completion status is incorrect");
    }

    @Test
    public void markAsDone_taskIsCompleted() {
        Task newTask = Task.newTask("Homework").markAsDone();
        assertEquals(newTask.getCompletionStatus(), true,
                "Task completion status is incorrect after marking as done");
    }
}
