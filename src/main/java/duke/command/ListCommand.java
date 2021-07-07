package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Implements list command objects.
 *
 * @author Audrey Felicio Anwar
 */
public class ListCommand extends Command {
    /**
     * Executes the given command.
     *
     * @param tasks Task list the user currently have.
     * @param ui Tool to interact with user.
     * @param storage Storage to load and save data.
     * @return Responses to be passed to user.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks(ui);
        return ui.getResponses();
    }
}
