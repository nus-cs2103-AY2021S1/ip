package duke.command;

import java.io.IOException;

import duke.core.MessageType;
import duke.core.Result;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.handle.TaskNotFoundException;



/**
 * The HelpCommand class describes the command that list the available commands.
 */
public class HelpCommand extends Command {

    /**
     * Takes in the task list, the interface, and the storage components, and lists
     * the available commands.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     * @return The result of the execution of the command.
     * @throws TaskNotFoundException If there is no task corresponding to the count of the task.
     * @throws IOException If the storage process needs to be handled.
     */
    @Override
    public Result excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException {
        return new Result(ui.getHelpMessage(),
                this.isContinuing(),
                MessageType.HELP_MESSAGE);
    }
}
