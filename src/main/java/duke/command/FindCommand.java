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
    public void execute(String command, TaskList list, Storage storage) {
        String horizontalLine = "____________________________________\n";
        String keyword = command.substring(5);
        System.out.println(horizontalLine + "Here are the things you want lor: \n");
        System.out.println(list.filter(keyword) + horizontalLine);
    }
}
