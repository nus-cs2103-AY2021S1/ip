package emily.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import emily.exception.DukeException;
import emily.task.Task;



class StorageTest {
    private Storage storage = new Storage("data/test.txt");

    @Test
    void loadData_success() {
        try {
            ArrayList<Task> ls = storage.loadData();
            String actualTask = ls.get(0).toString();
            String expectedTask = "[T][\u2713] loading data";
            assertEquals(actualTask, expectedTask);

        } catch (DukeException e) {
            assert false;
        }

    }

}
