package duke.command;

import duke.Storage;
import duke.task.Tasks;
import duke.Ui;

/**
 * The List command prints the task list.
 */
public class ListCommand extends Command {
    /**
     * The Command type.
     */
    private final CommandType commandType;

    /**
     * Instantiates a new List command.
     */
    public ListCommand() {
        this.commandType = CommandType.LIST;
    }

    /**
     * Prints the task list.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     */
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
