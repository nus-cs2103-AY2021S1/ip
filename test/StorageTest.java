import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StorageTest {
    @Test
    public void testReadFileContents() throws FileNotFoundException {
        TaskList actualList = new TaskList();
        File f = new File("test/testData/test.txt");
        Storage.readFileContents(f, actualList);

        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(new Todo("test 1"));
        expectedList.add(new Deadline("test 2", "by", "1986-04-08 12:30"));
        expectedList.add(new Event("test 3", "by", "1986-04-08 12:30"));

        assertEquals(expectedList.get(0).toString(), actualList.get(0).toString());
        assertEquals(expectedList.get(1).toString(), actualList.get(1).toString());
        assertEquals(expectedList.get(2).toString(), actualList.get(2).toString());

    }
}