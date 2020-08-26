package duke.command;

import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        taskList.listAllTasks();
    }
}
