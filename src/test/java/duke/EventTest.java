package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() {
        assertEquals("[E][\u2718] Sister birthday(at: 2019-10-15)", new Event("Sister birthday", "2019-10-15").toString());
    }
}
