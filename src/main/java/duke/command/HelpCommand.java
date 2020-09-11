package duke.command;

import duke.core.*;
import duke.handle.TaskNotFoundException;

import java.io.IOException;

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
     * @return
     * @throws TaskNotFoundException If there is no task corresponding to the count of the task.
     * @throws IOException If the storage process needs to be handled.
     */
    @Override
    public Result excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException {
        return new Result(ui.getHelpMessage(), this.isContinuing(), MessageType.HELP_MESSAGE);
    }
}
