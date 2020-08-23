package storage;

import tasks.Task;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    private Storage store = new Storage("test.txt");

    @Test
    public void load() {
        ArrayList<Task> actResult = this.store.load();
        StringBuilder actString = new StringBuilder();

        for (Task task: actResult) {
            actString.append(task);
        }
        String expResult = "[T][✘] read book[T][✘] return book";

        assertEquals(expResult, actString.toString());
    }
}
