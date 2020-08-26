package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    private static final String SAVEPATH = "./src/data/SaveData.txt";

    @Test
    public void saveTask_noExistingFile_success() {
        try {
            new Storage(SAVEPATH).saveTask(TaskList.createTaskList());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void loadTask_noExistingSave_success() {
        assertEquals(0, new Storage(SAVEPATH).loadTask().length());
    }
}
