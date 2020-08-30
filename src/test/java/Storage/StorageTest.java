package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import tasks.Task;

import org.junit.jupiter.api.Test;

import tasks.Task;

public class StorageTest {

    private final Storage store = new Storage("mug-test.txt");


    @Test
    public void load() {
        ArrayList<Task> actResult = this.store.load();
        StringBuilder actString = new StringBuilder();

        for (Task task: actResult) {
            actString.append(task);
        }
        String expResult = "[T][\u2718] read book[T][\u2718] return book";

        assertEquals(expResult, actString.toString());
    }
}
