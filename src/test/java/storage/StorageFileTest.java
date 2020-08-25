package test.java.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static src.test.java.util.TestUtil.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import src.main.java.duke.data.Duke;
import src.main.java.duke.data.exception.IllegalValueException;
import src.main.java.duke.data.task.Event;
import src.main.java.duke.data.task.Todo;
import src.main.java.duke.storage.StorageFile;

public class StorageFileTest {
    @TempDir
    public static Path testFolder;

    private static final String TEST_DATA_FOLDER = "ip/src/test/data/";
    private static final String NON_EXISTANT_FILE_NAME = "ThisFileDoesNotExist.txt";

    @Test
    public void constructor_nullFilePath_exceptionThrown() throws Exception {
        assertThrows(NullPointerException.class, () -> new StorageFile(null));
    }

    @Test
    public void constructor_noTxtExtension_exceptionThrown() throws Exception {
        assertThrows(IllegalValueException.class, () -> new StorageFile(TEST_DATA_FOLDER + "/" + "InvalidfileName"));
    }

    @Test
    public void load_validFormat() throws Exception {
        Duke actualAb = getStorage("ValidData.txt").load();
        Duke expectedAb = getTestDuke();

        // ensure loaded Duke is properly constructed with test data
        // TODO: overwrite equals method in Duke class and replace with equals method
        // below

        assertEquals(isIdentical(actualAb.getTaskList(), expectedAb.getTaskList()), true);
    }

    @Test
    public void load_nonExistantFile_returnsEmptyDuke() throws Exception {
        Duke actualAb = getStorage(NON_EXISTANT_FILE_NAME).load();
        Duke expectedAb = new Duke();

        assertEquals(actualAb, expectedAb);

        // verify that loading does not result in the file being created
        assertFileDoesNotExist(TEST_DATA_FOLDER + "/" + NON_EXISTANT_FILE_NAME);
    }

    @Test
    public void save_nullDuke_exceptionThrown() throws Exception {
        StorageFile storage = getTempStorage();
        assertThrows(NullPointerException.class, () -> storage.save(null));
    }

    @Test
    public void save_validDuke() throws Exception {
        Duke ab = getTestDuke();
        StorageFile storage = getTempStorage();
        storage.save(ab);
        assertStorageFilesEqual(storage, getStorage("ValidData.txt"));
    }

    // getPath() method in StorageFile class is trivial so it is not tested

    /**
     * Asserts that the contents of two storage files are the same.
     */
    private void assertStorageFilesEqual(StorageFile sf1, StorageFile sf2) throws Exception {
        assertTextFilesEqual(Paths.get(sf1.getPath()), Paths.get(sf2.getPath()));
    }

    private StorageFile getStorage(String fileName) throws Exception {
        return new StorageFile(TEST_DATA_FOLDER + "/" + fileName);
    }

    private StorageFile getTempStorage() throws Exception {
        return new StorageFile(testFolder.resolve("temp.txt").toString());
    }

    private Duke getTestDuke() throws Exception {
        Duke ab = new Duke();
        ab.addTask(new Event("mark homework", "2019-02-12 18:00"));
        ab.addTask(new Todo("asdfasf"));
        ab.addTask(new Event("mark homework", "2019-02-12 18:00"));
        return ab;
    }
}
