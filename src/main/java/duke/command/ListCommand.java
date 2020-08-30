package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "";
        if (tasks.getSize() == 0) {
            message += "You have no tasks currently :)";
        } else {
            message += "Here are the tasks in your list:";
            for (int n = 1; n <= tasks.getSize(); n++) {
                message += ("\n" + n + "." + tasks.getTask(n - 1));
            }
        }
        ui.showMessage(message);
        return message;
    }
}
