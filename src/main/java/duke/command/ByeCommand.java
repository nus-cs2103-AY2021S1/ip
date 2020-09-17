package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand implements Command {

    public ByeCommand() {
    }

    /**
     * {@inheritDoc}
     */
    public String execute(Storage storage, TaskList tasks, Runnable terminationFunction) throws DukeException {
        storage.saveToFile(tasks.toSaveFormat());
        terminationFunction.run();
        return Ui.showBye();
    }
}
