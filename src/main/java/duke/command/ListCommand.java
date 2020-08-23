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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof ListCommand) {
            return true;
        } else {
            return false;
        }
    }
}
