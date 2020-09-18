package seedu.duke;

import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    Storage testStorage;
    File file;

    public StorageTest() throws IOException {
        testStorage = new Storage("storageTest.txt");
        file = new File("storageTest.txt");
    }

    @Test
    public void is_file_created_test() {
        assertEquals(true,file.isFile(), "failed to create a file");
    }
}
