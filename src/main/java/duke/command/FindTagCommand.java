package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * FindTagCommand executes a filter on TaskList based on what tag user is finding.
 */
public class FindTagCommand extends Command {
    public FindTagCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    /**
     * Filters out tasks based on target tag
     * @param command User input
     * @param list TaskList object containing all tasks
     * @param storage Storage object that reads and writes to duke.txt
     * @return
     */
    @Override
    public String execute(String command, TaskList list, Storage storage) {
        try {
            String horizontalLine = "____________________________________\n";
            String targetTag = command.substring(8);
            String message;
            if (!targetTag.startsWith("#")) {
                return Warnings.invalidTagWarning();
            }
            message = list.filterTag(targetTag);
            return horizontalLine + "Here are the things you want lor: \n"
                    + message + horizontalLine;
        } catch (Exception ex) {
            return Warnings.invalidFindTagWarning();
        }
    }
}
