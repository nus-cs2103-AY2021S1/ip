import duke.DukeException;

import duke.backend.Storage;

import duke.task.Task;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    private static final String FILEPATH = "data/test.txt";
    private static final String NON_FILEPATH = "";

    @Test
    public void loadFile() throws FileNotFoundException, DukeException {
        Storage storage = new Storage(FILEPATH);
        ArrayList<Task> tasks = storage.loadFile();
        assertEquals(tasks.get(0).toString(), "[T][\u2713] this");
        assertEquals(tasks.get(1).toString(), "[D][\u2718] this - 2020-09-15");
        assertEquals(tasks.get(2).toString(), "[E][\u2713] this - 2020-09-15");
    }

    @Test
    public void loadFile_fileNotFound() throws FileNotFoundException, DukeException {
        Storage storage = new Storage(NON_FILEPATH);
        String error = "";
        try {
            storage.loadFile();
        } catch (FileNotFoundException e) {
            error = e.getMessage();
        }
        assertEquals(error, "No file found");
    }
}
