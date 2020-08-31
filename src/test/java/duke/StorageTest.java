package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageTest {

    private Storage storage = new Storage("src/main/java/data/duke.txt");

    @Test
    public void testGetFilePath() {
        assertEquals("src/main/java/data/duke.txt", storage.getFilePath());
    }
}
