import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void load_fileWithToDoRunUndoneTask_arrayListContainingUndoneRunToDoTask() throws Exception {
        assertEquals(new ArrayList<Task>(Arrays.asList(new ToDo("run"))), 
                new Storage("./data/jUnitInputTest.txt").load());
    }
}
