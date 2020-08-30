import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Todo;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][✘] Play brawl stars", new Todo("Play brawl stars").toString());
    }
}
