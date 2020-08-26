import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void descTest() throws DukeEmptyDescException {
        assertEquals(new Task("Hi", TaskType.TODO, false).toString(), "[✘] Hi");
    }

    @Test
    public void descTest2() throws DukeEmptyDescException {
        assertEquals(new Task("Hello There!", TaskType.TODO, true).toString(), "[✓] Hello There!");
    }

}
