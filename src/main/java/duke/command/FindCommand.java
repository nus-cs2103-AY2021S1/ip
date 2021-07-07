package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * Represents a find command to find tasks which match a particular search description.
 */
public class FindCommand extends Command {

    private String[] searchKeywords;

    /**
     * Initializes a find command.
     *
     * @param searchKeywords The search keywords stored as a String array.
     */
    public FindCommand(String[] searchKeywords) {
        this.searchKeywords = searchKeywords;
    }

    /**
     * Generates a task list with descriptions containing the search description and
     * prints each task in the list.
     *
     * @param taskList The existing task list.
     * @param ui       The UI instance which handles Duke's user interface.
     * @param storage  The existing storage for Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.processResultTaskList(taskList.generateResultTaskList(searchKeywords));
    }

    /**
     * Returns if the program should continue running at the current point in time.
     * If not, the program should be exited.
     *
     * @return If the program should continue running.
     */
    @Override
    public boolean isInProgram() {
        return true;
    }
}
