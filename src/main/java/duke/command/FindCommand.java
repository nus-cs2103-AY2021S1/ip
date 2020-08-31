package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * FindCommand executes a filter on TaskList based on what user is finding
 */
public class FindCommand extends Command {
    public FindCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    @Override
    public String execute(String command, TaskList list, Storage storage) {
        String horizontalLine = "____________________________________\n";
        String keyword = command.substring(5);
        return horizontalLine + "Here are the things you want lor: \n"
            + list.filter(keyword) + horizontalLine;
    }
}
