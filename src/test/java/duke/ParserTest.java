package duke;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testByeCommand() {
        try {
            String[] actual = Parser.parse("bye    ");
            assertEquals("bye", actual[0]);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testDoneCommand_notNumber_exceptionThrown() {
        try {
            Parser.parse("done hah");
            fail();
        } catch (DukeException e) {
            assertEquals(" ☹ OOPS!!! The argument of done command is not a number.", e.msg);
        }
    }

    @Test
    void testDoneCommand() {
        try {
            String[] expected = {"done", "3", null};
            String[] actual = Parser.parse("done 3");
            assertArrayEquals(expected, actual);
        } catch (DukeException e) {
            fail();
        }
    }
}
