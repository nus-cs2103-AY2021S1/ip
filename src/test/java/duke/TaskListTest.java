package duke;

import duke.exception.WriteToStorageException;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void getStore() {
        //TODO
    }
    @Test
    void addTask() throws WriteToStorageException {
        Task task = new Todo("hello world");
        TaskList tasks =  new TaskList(new StorageStub());
        assertEquals(0 , tasks.size());
        tasks.addTask(task);
        assertEquals(1 , tasks.size());
    }

    @Test
    void doneTask() throws WriteToStorageException {
        Task task = new Todo("hello world");
        TaskList tasks =  new TaskList(new StorageStub());
        tasks.addTask(task);
        tasks.doneTask(0);
        assertTrue(tasks.getTask(0).isDone());
    }

    @Test
    void testToString() {
    }

    @Test
    void getTask() {
    }

    @Test
    void size() {
    }

    @Test
    void remove() {
    }
}