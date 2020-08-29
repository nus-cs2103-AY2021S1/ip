package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

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
