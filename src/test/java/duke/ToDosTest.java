package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

class ToDosTest {
    @Test
    public void testToDos() {
        assertEquals("T | 0 | meeting", new ToDos("meeting").writeToFile());
    }
}