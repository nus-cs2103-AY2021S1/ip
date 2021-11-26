package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageTest {

    private final Storage storage = new Storage("./data/duke.txt");

    @Test
    public void testGetFilePath() {
        assertEquals("./data/duke.txt", storage.getFilePath());
    }
}
