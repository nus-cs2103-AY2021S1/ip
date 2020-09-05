import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void load_invalidFilePath_exceptionThrown() {
        try {
            String wrongPath = "data/randomFile.txt";
            new Storage(wrongPath).load();
            fail();
        } catch (DukeException error) {
            assertEquals("File cannot be loaded from the specified file path. Please try again!",
                                  error.getMessage());
        }
    }

    @Test
    public void changeFile_validInput_success() {
        Storage storage = new Storage("data/duke.txt");
        storage.changeFile();
        assertTrue(storage.isFileChanged);
    }
}
