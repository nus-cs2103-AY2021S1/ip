import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void loadEmptyFile() {
        Storage storage = new Storage(Paths.get(System.getProperty("user.dir"), "empty.txt"));
        try {
            assertEquals(storage.load().size(), 0);
        } catch (DukeException dukeException) {
            fail();
        }
    }
}
