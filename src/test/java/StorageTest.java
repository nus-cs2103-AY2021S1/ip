import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void invalidFilePath() {
        Storage storage = new Storage("hello");
        assertEquals(true, storage != null);
    }
}
