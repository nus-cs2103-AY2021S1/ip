package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void checkStringOutput() throws DukeException {
        Event e = new Event("workshop", "2020-08-28");
        assertEquals("[E][ ] workshop (at: Aug 28 2020)", e.toString());
        e.markAsDone();
        assertEquals("[E][X] workshop (at: Aug 28 2020)", e.toString());
    }

    @Test
    public void checkFileFormat() throws DukeException {
        Event e = new Event("birthday party", "2040-01-01");
        assertEquals("E , 0 , birthday party , 2040-01-01", e.fileFormat());
        e.markAsDone();
        assertEquals("E , 1 , birthday party , 2040-01-01", e.fileFormat());
    }

}
