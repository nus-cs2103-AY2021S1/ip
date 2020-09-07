package king;

import org.junit.jupiter.api.Test;

import storage.Storage;
import storage.StorageException;

public class StorageTest {

    @Test
    public void invalidFilePath() {
        final String invalidFilePath = "notAPath";
        try {
            Storage testStorage = new Storage(invalidFilePath);
        } catch (StorageException e) {
            System.out.println(e.message);
        }
    }
}
