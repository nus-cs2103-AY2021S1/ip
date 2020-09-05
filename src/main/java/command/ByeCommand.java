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
     * @param mainTasks The TaskList which stores unarchived tasks.
     * @param archivedTasks The TaskList which stores archived tasks.
     * @param storage The Storage for recording tasks passed in by user.
     * @return The output to be displayed to the user.
     */
    @Override
    public String execute(TaskList mainTasks, TaskList archivedTasks, Storage storage) {
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
