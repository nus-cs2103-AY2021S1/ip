package command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a DoNothingCommand.
 */
public class DoNothingCommand extends Command {

    /**
     * Does Nothing.
     *
     * @param tasks The TaskList manipulated by the Command.
     * @param storage The Storage which will record changes of tasks into the file specified by its path.
     * @return The output to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "";
    }

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return False since the program should still go on after doing nothing.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
