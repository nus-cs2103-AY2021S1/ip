import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    public class StorageStub extends Storage {
        public StorageStub(String path) {
            this.data = new File(path);
        }
    }

    @Test
    public void loadData_invalidFilePath_exceptionThrown() {
        StorageStub temp = new StorageStub("no where to be found");
        try {
            temp.loadData(new TaskList());
            fail();
        } catch (Exception e) {
            assertEquals("I can't seem to find your file...", e.getMessage());
        }
    }

    @Test
    public void loadData_invalidDataFromTxt_exceptionThrown() {
        StorageStub temp = new StorageStub("C:\\Users\\linco\\Desktop\\2103T IP\\data\\storageTest1.txt");
        try {
            temp.loadData(new TaskList());
            fail();
        } catch (Exception e) {
            assertEquals("Corrupted data detected! Loading terminated!", e.getMessage());
        }
    }

    @Test
    public void loadData_validData_success() throws DukeException{
        StorageStub temp = new StorageStub("C:\\Users\\linco\\Desktop\\2103T IP\\data\\storageTest2.txt");
        TaskList list = new TaskList();
        temp.loadData(list);
        assertEquals(list.getList().get(0).toString(), "[D][✘] return book (by: Dec 2 2019 18:00)");
    }

}
