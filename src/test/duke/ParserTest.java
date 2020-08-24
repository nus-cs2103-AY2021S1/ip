package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    public void testParse_listCommand_success() throws DukeException{
        assertEquals(new Command("list"), Parser.parse("list"));
    }

    @Test
    public void testParse_unknownCommand_exceptionThrown() {
        try {
            assertEquals(new Command("what"), Parser.parse("what"));
            fail();
        } catch (Exception e) {
            assertEquals("Oh dear! I'm sorry, but I don't know what that means :(", e.getMessage());
        }
    }
}