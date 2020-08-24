package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {

    @Test
    public void constructor_emptyDescription_taskException() {
        assertThrows(TaskException.class, () -> new Todo(""));
    }

    @Test
    public void getTaskType() {
        try {
            Todo t = new Todo("Some desc");
            assertEquals(t.getTaskType(), TaskType.TODO);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toStorageString() {
        try {
            Todo t = new Todo("DESC");
            assertEquals("DESC :: ✘", t.toStorageString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseStorageString_validString() {
        try {
            String validString = "DESC :: [✘]";
            Todo t = Todo.parseStorageString(validString);
            assertTrue(t.equals(new Todo("DESC")));
        } catch (Exception e) {
            fail();
        }
    }
}
