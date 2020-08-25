import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    
    @Test
    void toStringTest() {
        Event e1 = new Event("party", "12-2-2020 2000");
        String expected = "[E]" + "[" +  "\u2718"  + "] " + "party" + " (at: 12-2-2020 2000)";
        assertEquals(e1.toString(), expected);
    }
}
