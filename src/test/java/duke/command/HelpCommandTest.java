package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

/**
 * Test to ensure that help command is correct.
 */
class HelpCommandTest extends CommandTests {

    /**
     * Execution of help command.
     */
    @Test
    void execute_correctDisplay_success() {
        try {
            HelpCommand helpCommand = new HelpCommand();
            String expected = "Here are the available commands that I know:\n"
                    + "1. todo _ (e.g. todo 3)\n"
                    + "2. deadline 'task name' /by 'end time' (e.g. deadline Exercise /by 23-8-20)\n"
                    + "3. event 'task name' /at 'start time - end time' (e.g. meeting /at Sunday 2pm - 4pm)\n"
                    + "4. list\n"
                    + "5. done _ (e.g. done 4)\n"
                    + "6. delete _ (e.g. delete 4)\n"
                    + "7. find '   ' (e.g. find book)\n"
                    + "8. sort\n"
                    + "9. bye\n";
            assertEquals(expected, executeTask(helpCommand));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

}
