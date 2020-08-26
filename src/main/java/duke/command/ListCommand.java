package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * ListCommand is an extension of a command.
 * It prints all the tasks that is in the TaskList.
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
