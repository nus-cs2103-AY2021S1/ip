package duke;

import main.java.duke.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void invalidTaskTest() throws EmptyDescriptionException {
        Parser parser = new Parser();
        Task response = parser.handleInput("invalidtask homework");
        assertEquals(null, response);
    }
}
