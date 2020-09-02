import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StorageTest {
    @Test
    public void loadEmptyFile() {
        Storage storage = new Storage(Paths.get(System.getProperty("user.dir")));
        assertEquals(storage.load().size(), 0);
    }
}
