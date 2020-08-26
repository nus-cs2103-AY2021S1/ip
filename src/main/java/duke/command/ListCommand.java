package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command, false);

    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.tasks();
        list.showTasks();
    }
}
