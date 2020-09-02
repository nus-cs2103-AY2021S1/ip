package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(MESSAGE_EXIT_ACKNOWLEDGEMENT);
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
