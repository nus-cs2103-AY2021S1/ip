package duke;

import duke.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    public void containsString_arrayWithString_true() {
        assertTrue(new Parser("todo borrow book")
                .containsString("return book", new String[]{"return book"}));
    }

    @Test
    public void containsString_arrayWithoutString_false() {
        assertFalse(new Parser("todo borrow book")
                .containsString("return library book", new String[]{}));
    }

    @Test
    public void parse_eventCommand_success() {
        try {
            Command myReturnedTask = new Parser("event give speech /at 2021-08-26 2000").parse();
            assertNotNull(myReturnedTask); //check if the object is != null
            //check if the returned object is of class Task
            assertTrue(myReturnedTask instanceof Command);
        } catch(DukeException e) {
            // let the test fail, if the function throws a Duke Exception.
            fail("Got Duke Exception");
        }
    }

    @Test
    public void parse_emptyString_exceptionThrown() {
        try {
            Command myReturnedTask = new Parser("").parse();
            fail(); // the test should not reach this line
        } catch(DukeException e) {
            assertEquals("Your Input String cannot be Empty!", e.getMessage());
        }
    }

    @Test
    public void parse_unrecognizedString_exceptionThrown() {
        try {
            Command myReturnedTask = new Parser("yo").parse();
            fail(); // the test should not reach this line
        } catch(DukeException e) {
            assertEquals("Your Input Command is not Recognized!", e.getMessage());
        }
    }

    @Test
    public void getString_string_success() {
        assertEquals("todo go to market", new Parser("todo go to market").getString());
    }
}