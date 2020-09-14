package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Implements update command objects.
 *
 * @author Audrey Felicio Anwar
 */
public class UpdateCommand extends Command {
    private int index;
    private String type;
    private String newDetails;

    /**
     * Constructs a new update command object
     *.
     * @param index Index of item to be updated.
     * @param type Type of attribute to be updated.
     * @param newDetails Updated details to be saved.
     */
    public UpdateCommand(int index, String type, String newDetails) {
        this.index = index;
        this.type = type;
        this.newDetails = newDetails;
    }

    /**
     * Executes the given command.
     *
     * @param tasks Task list the user currently have.
     * @param ui Tool to interact with user.
     * @param storage Storage to load and save data.
     * @return Responses to be passed to user.
     * @throws DukeException If there is an error.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.updateTask(index, type, newDetails, ui);
        storage.saveTasks(tasks.getTasks());
        return ui.getResponses();
    }
}
