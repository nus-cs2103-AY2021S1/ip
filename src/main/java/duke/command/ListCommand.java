package duke.command;

import duke.Statistics;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Displays all current tasks.
     *
     * @param tasks Task list representing current tasks.
     * @param storage Storage Storage in charge of saving file to hard disk.
     * @return A string representing Duke's response after executing command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Statistics stats) {
        return tasks.listAllTasks();
    }

}
