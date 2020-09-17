package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exception.WriteToStorageException;
import duke.task.Task;
import duke.task.Todo;


class TaskListTest {

    @Test
    void addTask() throws WriteToStorageException {
        Task task = new Todo("hello world");
        TaskList tasks = new TaskList(new StorageStub());
        assertEquals(0 , tasks.size());
        tasks.addTask(task);
        assertEquals(1 , tasks.size());
    }

    @Test
    void doneTask() throws WriteToStorageException {
        Task task = new Todo("hello world");
        TaskList tasks = new TaskList(new StorageStub());
        tasks.addTask(task);
        tasks.doneTask(0);
        assertTrue(tasks.getTask(0).isDone());
    }
}
