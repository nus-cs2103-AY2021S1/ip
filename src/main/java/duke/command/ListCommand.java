package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            ui.displayMessage("No tasks on your agenda.");
        } else {
            ui.displayMessage(String.format("Here are the tasks in your agenda:\n%s", tasks.listTasks()));
        }
    }
}
