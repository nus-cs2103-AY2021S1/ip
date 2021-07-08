package duke.command;

import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.printTasks();
    }
}
