package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class InvalidCommand extends Command{
    /**
     * Represents an abstract Command forming the parent to other commands.
     *
     * @param command
     */
    public InvalidCommand(String command) {
        super(command, false);
    }

    /**
     * Executes an invalid command by informing the user that the command is invalid.
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.invalidCommandMessage();
    }

    /**
     * Executes an invalid command by informing the user that the command is invalid on FXML.
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    @Override
    public String executeChat(TaskList list, Ui ui, Storage storage) {
        return ui.invalidCommandMessage(true);
    }
}
