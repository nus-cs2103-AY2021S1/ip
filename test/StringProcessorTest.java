import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StringProcessorTest {
    @Test
    public void testReadFileContents() throws FileNotFoundException {
        ArrayList<Task> actualList = new ArrayList<>();
        File f = new File("test/testData/test.txt");
        StringProcessor.readFileContents(f, actualList);

        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(new Todo("test 1"));
        expectedList.add(new Deadline("test 2", "by", "today"));
        expectedList.add(new Event("test 3", "by", "today"));

        assertEquals(expectedList.get(0).toString(), actualList.get(0).toString());
        assertEquals(expectedList.get(1).toString(), actualList.get(1).toString());
        assertEquals(expectedList.get(2).toString(), actualList.get(2).toString());

    }
}