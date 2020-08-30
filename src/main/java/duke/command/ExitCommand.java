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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks.getTaskList());
            return ui.sayBye();
        } catch (IOException ex) {
            return ui.printExceptions(ex);
        }
    }

}
