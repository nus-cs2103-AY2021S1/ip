package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

public class StorageTest {
    @Test
    public void storageTest() throws IOException {
        TaskList taskList= new TaskList(new ArrayList<Task>());
        TaskList taskList2;
        taskList.addTask(new Todo("eat",false));
        Storage.loadFile();
        Storage.writeToFile(taskList);
        taskList2 = new TaskList(Storage.loadFile());
        for  (int i = 0; i<taskList.getTotalTask();i++) {
            assertEquals(taskList.get(i),taskList2.get(i));
        }
    }
    
}
