package storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void processLineTest() {
        Storage storage = new Storage("storage_test.txt");
        String lineToTest = "E | 1 | day6 concert | 2019-10-05";
        String outputLine = storage.processLine(lineToTest);
        assertEquals("[E][✓] day6 concert (at: Oct 5 2019)", outputLine);

    }

    @Test
    public void processLineTestError() {
        Storage storage = new Storage("storage_test.txt");
        String lineToTest = "something | else | yay";
        String outputLine = storage.processLine(lineToTest);
        assertEquals("There's been an error!", outputLine);

    }

}
