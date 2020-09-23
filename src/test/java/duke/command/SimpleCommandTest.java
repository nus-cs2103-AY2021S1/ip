package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidSimpleCommandException;
import duke.exception.InvalidTaskNumberException;

/**
 * Tests for the successive word after a simple command.
 */
public class SimpleCommandTest extends CommandTests {

    /**
     * Tests simple command when the succeeding word is not a number.
     */
    @Test
    public void checkValidity_invalidFormat_throwsInvalidSimpleCommandException() {
        SimpleCommand sc1 = new DoneCommand("");
        SimpleCommand sc2 = new DeleteCommand("awsdsa");
        // Tests
        assertThrows(InvalidSimpleCommandException.class, () -> executeTask(sc1));
        assertThrows(InvalidSimpleCommandException.class, () -> executeTask(sc2));
    }

    /**
     * Tests simple command when the succeeding word is not valid.
     */
    @Test
    public void checkValidity_invalidTaskNumber_throwsInvalidTaskNumberException() {
        String expectedMessage = String.format("OOPS!!! Task number does not exist in the list.\n"
            + "Your current list only has %d tasks!\n", 0);
        SimpleCommand sc1 = new DoneCommand("5");
        SimpleCommand sc2 = new DeleteCommand("-2");
        // Tests
        InvalidTaskNumberException e = assertThrows(
            InvalidTaskNumberException.class, () -> executeTask(sc1));
        assertEquals(expectedMessage, e.getMessage());
        InvalidTaskNumberException e2 = assertThrows(
            InvalidTaskNumberException.class, () -> executeTask(sc2));
        assertEquals(expectedMessage, e2.getMessage());
    }

}
