import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
        assertEquals(storage.isFileChanged, true);
    }
}
