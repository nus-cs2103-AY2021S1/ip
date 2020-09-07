package duke.utils;

import duke.tasks.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    TaskList list = new TaskList();
    Storage storage = new Storage(list);

    @Test
    void readSavedFile() {
        String actual = storage.readSavedFile();
        String expected = "Hold a while, trying to retrieve where you were last time...\n"
                + "Great! We have successfully loaded the data. Enjoy~\n";
        assertEquals(expected, actual);
    }

    @Test
    void saveDataToFile() {
        String actual = storage.saveDataToFile();
        String expected = "Content Saved! Hope to see you again soon!";
        assertEquals(expected, actual);
    }
}
