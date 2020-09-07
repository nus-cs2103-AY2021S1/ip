package duke.utils;

import duke.tasks.TaskList;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    TaskList list = new TaskList();
    Storage storage = new Storage(list);

    @Test
    void readSavedFile() throws IOException {
        makeBackup();
        String actual = storage.readSavedFile();
        String expected = "Hold a while, trying to retrieve where you were last time...\n"
                + "Great! We have successfully loaded the data. Enjoy~\n";
        recover();
        assertEquals(expected, actual);
    }

    @Test
    void saveDataToFile() throws IOException {
        makeBackup();
        String actual = storage.saveDataToFile();
        String expected = "Content Saved! Hope to see you again soon!";
        assertEquals(expected, actual);
        recover();
    }

    private void makeBackup() throws IOException {
        File f = new File("data/duke.txt");
        File dest = new File("data/duke.bak");
        File test = new File("src/test/java/duke/utils/duke-test.txt");
        dest.exists();
        if (f.exists()) {
            Files.copy(f.toPath(), dest.toPath());
        }
        if (f.delete()) {
            Files.copy(test.toPath(), f.toPath());
        }
    }

    private void recover() throws IOException {
        File dest = new File("data/duke.bak");
        File f = new File("data/duke.txt");
        if (f.delete()) {
            Files.copy(dest.toPath(), f.toPath());
        }
        dest.delete();
    }
}
