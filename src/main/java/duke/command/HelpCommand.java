package duke.command;

import duke.backend.Storage;
import duke.response.Response;
import duke.task.TaskList;

/**
 * Represents a list of available instructions.
 */
public class HelpCommand implements Command {

    /**
     * Returns false because command does not exit.
     *
     * @return false.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }

    /**
     * Performs the action to be taken.
     *
     * @param tasks   The TaskList to add the task to.
     * @param ui      The Ui to show responses or error messages.
     * @param storage The Storage to save the TaskList.
     * @return True if Duke should continue running.
     */
    @Override
    public String execute(TaskList tasks, Response ui, Storage storage) {
        String result = "--------Instructions-------- \n";
        for (CommandType type: CommandType.values()) {
            result += type.toString();
        }
        result += "\nTry me out!";
        return result;
    }
}
