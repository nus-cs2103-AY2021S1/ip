package duke.commands;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.tasks.TaskList;


public class ScheduleCommandTest {
    private Command command = new ScheduleCommand("2020-09-20");
    private Storage storage = new Storage();
    private TaskList list = new TaskList();

    @Test
    public void scheduleCommandExecution_dateDoesNotExist_exceptionThrown() {
        try {
            command.execute(list, storage);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Hi my friend, No events/deadlines with this date!");
        }
    }
}
