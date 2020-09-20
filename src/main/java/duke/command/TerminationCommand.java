package duke.command;

import duke.task.TaskList;
import duke.dukeexception.DukeException;
import duke.Ui;
import duke.Storage;

import java.io.IOException;

/**
 * Command for terminating or stopping the app,
 * which includes saving the current taskList to data file.
 */
public class TerminationCommand extends Command {
    public TerminationCommand() {}

    public String execute(TaskList taskList, Storage storage) throws DukeException {
        try {
            storage.save(taskList);
            return Ui.showGoodbye();
        } catch (IOException e) {
            throw new DukeException("Something went wrong saving the tasks to data file");
        }
    }

    public boolean isExit() {
        return true;
    }
}
