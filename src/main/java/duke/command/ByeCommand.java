package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Tasks;

/**
 * The Bye command which terminates the program.
 */
public class ByeCommand extends Command {
    /**
     * Instantiates a new Bye command.
     */
    public ByeCommand() {
        this.commandType = CommandType.BYE;
    }

    /**
     * Print Exit Message and terminates the program.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     */
    @Override
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
    }
}
