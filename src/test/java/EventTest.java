import main.java.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        assertEquals("[E][\u2718] read book(at: night)",
                new Event(" read book", " night").toString());
    }
}
