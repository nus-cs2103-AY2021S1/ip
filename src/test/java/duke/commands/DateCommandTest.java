package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateCommandTest {
    private Command command = new DateCommand("2020-09-20");
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private TaskList list = new TaskList();

    @Test
    public void dateDoesNotExist_exceptionThrown() {
        try {
            command.execute(list, ui, storage);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Hi my friend, No events/deadlines with this date!");
        }
    }
}
