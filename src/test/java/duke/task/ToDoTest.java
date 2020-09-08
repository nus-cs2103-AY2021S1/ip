package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.UnreadableSaveTaskException;

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
