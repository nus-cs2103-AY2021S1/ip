package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * FindCommand executes a filter on TaskList based on what user is finding.
 */
public class FindCommand extends Command {
    public FindCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    /**
     * Filters out tasks from task list based on user input.
     * @param command User input
     * @param list TaskList object containing all tasks
     * @param storage Storage object that reads and writes to duke.txt
     * @return A list of filtered tasks
     */
    @Override
    public String execute(String command, TaskList list, Storage storage) {
        try {
            String horizontalLine = "____________________________________\n";
            String keyword = command.substring(5);
            return horizontalLine + "Here are the things you want lor: \n"
                    + list.filter(keyword) + horizontalLine;
        } catch (Exception ex) {
            return Warnings.invalidFindWarning();
        }
    }
}
