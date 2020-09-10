package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    private static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "bb cya again!";

    /**
     * Displays acknowledgement message that task has been exited.
     *
     * @param tasks Task list representing current tasks.
     * @param ui User interface interacting with user.
     * @param storage Storage Storage in charge of saving file to hard disk.
     * @return A string representing Duke's response after executing command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return MESSAGE_EXIT_ACKNOWLEDGEMENT;
    }

    /**
     * Checks if program stops running upon command.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
