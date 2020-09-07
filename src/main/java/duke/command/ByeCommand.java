package duke.command;

import duke.History;
import duke.Storage;
import duke.task.TaskList;

/**
 * Command when user exits Duke program.
 */
public class ByeCommand extends Command {
    /**
     * Prints out goodbye message to console before shutting down Duke.
     * @param taskList the List containing all the tasks that Duke has stored.
     * @param storage the database for Duke to save all tasks to the user's local storage.
     * @param history the state of all changes made to Duke's TaskList.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, History history) {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Determine whether or not Duke should shut down after running this command, only true for ByeCommand.
     *
     * @return a boolean specifying whether or not Duke will shut down after the command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
