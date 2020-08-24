import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeFileTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void saveRecordTest() {
        DukeFile dukeFile = DukeFile.createDukeFile("test");
        dukeFile.saveRecord("Testing String");
        assertEquals(1, dukeFile.getRecords().size());
    }

    @Test
    public void updateRecordTest() {
        DukeFile dukeFile = DukeFile.createDukeFile("test");
        dukeFile.saveRecord("Testing String");
        dukeFile.updateRecord("Tested String", 1);
        assertEquals(1, dukeFile.getRecords().size());
        assertEquals("Tested String", dukeFile.getRecords().get(0));
    }


}
