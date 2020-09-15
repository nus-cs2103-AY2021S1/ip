package ekud.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ekud.exceptions.DukeUnrecognisedCommandException;


public class CommandTest {

    @Test
    public void createCommand_done_done() {
        try {
            assertEquals(Command.createCommand("done"), Command.DONE);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void createCommand_unknown_exceptionThrown() {
        try {
            Command.createCommand("doe");
            fail();
        } catch (DukeUnrecognisedCommandException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }
}
