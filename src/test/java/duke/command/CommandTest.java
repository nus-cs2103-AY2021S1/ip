package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.TaskList;

/**
 * Test class for the command package.
 */
public class CommandTest {

    @Test
    void testCommand_correctResponse() {
        Command command = new InvalidCommand();
        try {
            command.executeWithResponse();
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.toString());
        }
        TaskList taskList = new TaskList();
        String userInput = "event James' party /at 5pm tomorrow";
        try {
            command = new EventCommand(userInput, taskList);
            assertEquals(
                    "Got it, I've added this event: [E][X] James' party (at: 5pm tomorrow)\n"
                             + "Now you have 1 tasks in the list.",
                    command.executeWithResponse()
            );
        } catch (DukeException e) {
            System.out.println("Test for Event command failed.");
            fail();
        }
        try {
            command = new DeleteCommand("delete 3", taskList);
            command.executeWithResponse();
            fail();
        } catch (DukeException e) {
            assertEquals("Hey! That number is not in the list!", e.toString());
        }
    }

}
