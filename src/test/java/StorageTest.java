import duke.storage.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void saveRecordTest() {
        Storage storage = Storage.createDukeFile("test");
        storage.saveRecord("Testing String");
        assertEquals(1, storage.getRecords().size());
    }

    @Test
    public void updateRecordTest() {
        Storage storage = Storage.createDukeFile("test");
        storage.saveRecord("Testing String");
        storage.updateRecord("Tested String", 1);
        assertEquals(1, storage.getRecords().size());
        assertEquals("Tested String", storage.getRecords().get(0));
    }


}
