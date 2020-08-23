package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String echoizer = "\t Got it. I've added this task:\n"
                + "\t %s\n"
                + "\t Now you have %d tasks in the list.\n";
        ui.showAction(String.format(echoizer, task, tasks.size()));
    }
}
