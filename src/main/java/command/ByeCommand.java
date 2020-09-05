package command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a ByeCommand for exiting the program.
 */
public class ByeCommand extends Command {

    /**
     * Exits the program
     *
     * @param tasks The TaskList manipulated by the program
     * @param storage The Storage for recording tasks passed in by user.
     * @return The output to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "\t Bye. Hope to see you again soon!\n";
    }

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return True since a ByeCommand indicates that the user wants to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
