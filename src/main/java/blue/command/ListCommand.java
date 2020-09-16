package blue.command;

import blue.Storage;
import blue.task.Tasks;
import blue.ui.Ui;

/**
 * The List command prints the task list.
 */
public class ListCommand extends Command {
    /**
     * Instantiates a new List command.
     */
    public ListCommand() {
        this.commandType = CommandType.LIST;
    }

    /**
     * Returns a response consisting a message with the task list.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @return the response to list command.
     */
    @Override
    public CommandResponse execute(Tasks tasks, Ui ui, Storage storage) {
        return new CommandResponse(ui.getListMessage(tasks), this.isExit());
    }
}
