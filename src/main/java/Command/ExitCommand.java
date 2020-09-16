package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ExitCommand is the termination command for Duke.
 *
 * @author Joshua
 */
public class ExitCommand extends Command {

    /**
     * Terminates Duke and informs the Ui to inform the User.
     *
     * @param taskList the TaskList to be updated.
     * @param ui       the Ui that interacts with the user.
     * @param storage  the Storage that is updated with TaskList.
     * @return output to be displayed to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        output = ui.showExit();
        return output;
    }
}

