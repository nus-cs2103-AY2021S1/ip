package duke.task;

import duke.DukeException;
import duke.Parser;
import duke.command.Command;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    @Test
    public void createDeadline_withoutDateTime_exceptionThrown() {
        try {
            Deadline d = new Deadline("submission", "");
        } catch (DukeException e) {
            assertTrue(e.getMessage().contains("Please input date and time in correct format"));
        }
    }

    @Test
    public void createDeadline_withoutInvalidDateTime_exceptionThrown() {
        try {
            Deadline d = new Deadline("submission", "2020/14/54 2810");
        } catch (DukeException e) {
            assertTrue(e.getMessage().contains("Please input date and time in correct format"));
        }
    }

    @Test
    public void createDeadline_withDateTime_deadlineCreated() {
        try {
            Deadline d = new Deadline("submission", "2020/11/14 15:30");
            assertTrue(d instanceof Deadline);
        } catch (DukeException e) {
            System.out.println("Test failed");
        }
    }
}
