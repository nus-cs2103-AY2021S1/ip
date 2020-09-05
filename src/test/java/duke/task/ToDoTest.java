package duke.task;

import duke.exception.UnreadableSaveTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {
    @Test
    public void factoryMethod_success() {
        String[] data = new String[]{"T", "0", "test event"};
        try {
            assertEquals(ToDo.createFromFile(data), ToDo.create("test event"));
        } catch (UnreadableSaveTaskException e) {
            e.printStackTrace();
            fail();
        }
    }
}
