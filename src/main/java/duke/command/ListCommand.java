package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String tasksToString = tasks.tasksToString();
        ui.printMessage(tasksToString);
    }
}
