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
     * @param mainTasks The TaskList which stores unarchived tasks.
     * @param archivedTasks The TaskList which stores archived tasks.
     * @param storage The Storage which will record changes of tasks into the file specified by its path.
     * @return The output to be displayed to the user.
     */
    @Override
    public String execute(TaskList mainTasks, TaskList archivedTasks, Storage storage) {
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
