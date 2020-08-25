package duke.command;

import duke.DukeException;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class AddCommandTest {

    @Test
    public void containsString_arrayWithString_true() {
        assertTrue(new AddCommand(new String[]{"deadline", "return book", "/by", "2022-08-26", "1800" })
                .containsString("return book"));
    }

    @Test
    public void containsString_arrayWithoutString_false() {
        assertFalse(new AddCommand(new String[]{"deadline", "return book", "/by", "2022-08-26", "1800" })
                .containsString("return library book"));
    }

    @Test
    public void processTask_deadlineTask_success() {
        try {
            Task myReturnedTask = new AddCommand(new String[]{"deadline", "return", "book",
                    "/by", "2022-08-26", "1800" }).processTask("/by", "deadline");
            assertNotNull(myReturnedTask); //check if the object is != null
            //check if the returned object is of class Task
            assertTrue(myReturnedTask instanceof Task);
        } catch(DukeException e) {
            // let the test fail, if the function throws a Duke Exception.
            fail("Got Duke Exception");
        }
    }

    @Test
    public void processTask_deadlineNoDate_exceptionThrown() {
        try {
            Task myReturnedTask = new AddCommand(new String[]{"deadline", "return", "book",
                    "/by" }).processTask("/by", "deadline");
            fail(); // the test should not reach this line
        } catch(DukeException e) {
            assertEquals("All deadline/event tasks must come with a date in yyyy-mm-dd format!",
                    e.getMessage());
        }
    }

}