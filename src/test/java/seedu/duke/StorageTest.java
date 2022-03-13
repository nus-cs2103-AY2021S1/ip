package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class StorageTest {
    private Storage testStorage;
    private File file;

    public StorageTest() throws IOException {
        testStorage = new Storage("storageTest.txt");
        file = new File("storageTest.txt");
    }

    @Test
    public void isFileCreatedTest() {
        assertEquals(true, file.isFile(), "failed to create a file");
    }
}
