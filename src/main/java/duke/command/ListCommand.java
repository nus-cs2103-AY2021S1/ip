package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.printTasks();
    }
}
