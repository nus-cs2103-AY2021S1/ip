package duke.command;

import duke.storage.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Encapsulates a command which facilitates leaving the Duke program.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks.getTaskList());
            ui.sayBye();
        } catch (IOException ex) {
            ui.printExceptions(ex);
        }
    }

    @Override
    public boolean isDone() {
        return true;
    }

}
