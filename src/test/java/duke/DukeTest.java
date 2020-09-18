package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void eventTest() {
        Event e = new Event("meeting", "school");
        assertEquals(e.getIndicator(), "[E]");
    }

    @Test
    public void deadlineTest() {
        Deadline d = new Deadline("hw", "monday");
        assertEquals(d.getIndicator(), "[D]");
    }

    @Test
    public void todoTest() {
        Todo t = new Todo("say hbd");
        assertEquals(t.getIndicator(), "[T]");
    }
}
