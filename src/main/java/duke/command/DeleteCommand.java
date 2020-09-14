package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Implements delete command objects.
 *
 * @author Audrey Felicio Anwar
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the given command.
     *
     * @param tasks Task list the user currently have.
     * @param ui Tool to interact with user.
     * @param storage Storage to load and save data.
     * @return Responses to be passed to user.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.deleteTask(index, ui);
        storage.saveTasks(tasks.getTasks());
        return ui.getResponses();
    }
}
