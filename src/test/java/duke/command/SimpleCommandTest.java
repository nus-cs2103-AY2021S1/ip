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
    public void testNotNumber() {
        SimpleCommand sc1 = new DoneCommand("");
        SimpleCommand sc2 = new DeleteCommand("awsdsa");
        // Tests
        assertThrows(InvalidSimpleCommandException.class, () -> sc1.execute(taskList, ui, storage));
        assertThrows(InvalidSimpleCommandException.class, () -> sc2.execute(taskList, ui, storage));
    }

    /**
     * Tests simple command when the succeeding word is not valid.
     */
    @Test
    public void testInvalidTaskNumber() {
        String expectedMessage = String.format("OOPS!!! Task number does not exist in the list.\n"
            + "Your current list only has %d tasks!\n", 0);
        SimpleCommand sc1 = new DoneCommand("5");
        SimpleCommand sc2 = new DeleteCommand("-2");
        // Tests
        InvalidTaskNumberException e = assertThrows(
            InvalidTaskNumberException.class, () -> sc1.execute(taskList, ui, storage));
        assertEquals(expectedMessage, e.getMessage());
        InvalidTaskNumberException e2 = assertThrows(
            InvalidTaskNumberException.class, () -> sc2.execute(taskList, ui, storage));
        assertEquals(expectedMessage, e2.getMessage());
    }
}
