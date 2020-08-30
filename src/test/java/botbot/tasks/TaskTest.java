package botbot.tasks;

import botbot.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void getType() {
        try {
            assertEquals('D', new Deadline("deadline test /by 1-1-2020").getType());
            assertEquals('D', new Deadline("test", true, "2020-01-01T03:14:15.926535898").getType());
            assertEquals('E', new Event("event sample /at 31-12-2020 2359").getType());
            assertEquals('E', new Event("sample", false, "2020-12-31T00:00").getType());
            assertEquals('T', new Todo("todo test").getType());
            assertEquals('T', new Todo("test", true).getType());
        } catch (InvalidFormatException e) {
            fail();
        }
    }

    @Test
    public void getDescription() {
        try {
            assertEquals("test", new Deadline("deadline test /by 1-1-2020").getDescription());
            assertEquals("test", new Deadline("test", true, "2020-01-01T03:14:15.926535898").getDescription());
            assertEquals("sample", new Event("event sample /at 31-12-2020 2359").getDescription());
            assertEquals("sample", new Event("sample", false, "2020-12-31T00:00").getDescription());
            assertEquals("test", new Todo("todo test").getDescription());
            assertEquals("test", new Todo("test", true).getDescription());
        } catch (InvalidFormatException e) {
            fail();
        }
    }

    @Test
    public void getStatus() {
        try {
            assertEquals("0", new Deadline("deadline test /by 1-1-2020").getStatus());
            assertEquals("1", new Deadline("test", true, "2020-01-01T03:14:15.926535898").getStatus());
            assertEquals("0", new Event("event sample /at 31-12-2020 2359").getStatus());
            assertEquals("0", new Event("sample", false, "2020-12-31T00:00").getStatus());
            assertEquals("0", new Todo("todo test").getStatus());
            assertEquals("1", new Todo("test", true).getStatus());
        } catch (InvalidFormatException e) {
            fail();
        }
    }
}
