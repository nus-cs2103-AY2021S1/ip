package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Encapsulates a list command to be executed by Duke.
 * Prints all the tasks that is in the TaskList.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        taskList.listAllTasks();
    }
}
