package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command when user exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Returns true since this is an exit command.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * No execution needed.
     * @param taskList the list of tasks user has
     * @param ui ui instance to display messages
     * @param storage storage instance to manage storing on disk
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "bye!";
    }
}
