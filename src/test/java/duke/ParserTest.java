package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void getCommand() {
        Parser parser = new Parser("todo read book");
        assertEquals("todo", parser.getCommand());
    }
}
