package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.Tasks;

/**
 * The Bye command which terminates the program.
 */
public class ByeCommand extends Command {
    /**
     * The Command type.
     */
    private final CommandType commandType;

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
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        System.exit(0);
    }
}
