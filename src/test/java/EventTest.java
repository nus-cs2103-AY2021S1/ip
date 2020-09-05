import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EventTest {
    @Test
    void toStringTest_getStringOfEventTask_returnsExpectedString() {
        Event e1 = new Event("party", "12-2-2020 2000");
        String expected = "[E]" + "[" + "\u2718" + "] " + "party" + " (at: 12-2-2020 2000)";
        assertEquals(e1.toString(), expected);
    }
}
