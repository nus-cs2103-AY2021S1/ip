package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    Storage storage = new Storage("src/main/java/data/duke.txt");

    @Test
    public void testGetFilePath() {
        assertEquals("src/main/java/data/duke.txt", storage.getFilePath());
    }
}
