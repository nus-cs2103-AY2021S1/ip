package duke.command;

import duke.command.Command;
import duke.task.TaskList;
import duke.dukeexception.DukeException;
import duke.Ui;
import duke.Storage;

import java.io.IOException;

public class TerminationCommand extends Command {
    public TerminationCommand() {}

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            storage.save(taskList);
            return ui.showGoodbye();
        } catch (IOException e) {
            throw new DukeException("Something went wrong saving the tasks to data file");
        }
    }

    public boolean isExit() {
        return true;
    }
}
