import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    String home = System.getProperty("user.home");
    File testFile = new File(home + "/ip/src/main/java/Data/TestFile.txt");

    @Test
    void generateList_validFile() {
        TaskList taskList = new TaskList();

        try {
            taskList.generateList(testFile);
            assertEquals(2, taskList.size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void generateList_nonExistentFile() {
        TaskList taskList = new TaskList();

        try {
            File file = new File("testFile.txt");
            taskList.generateList(file);
        } catch (FileNotFoundException e) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void size_correctSize() {
        TaskList list = new TaskList();

        try {
            list.generateList(testFile);
            list.add(new Task("test"));
            assertEquals(3, list.size());
        } catch (Exception e) {
            fail();
        }
    }

}