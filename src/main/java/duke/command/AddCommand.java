package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand implements Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.print("Got it. I've added this task:", "\t" + task,
                         "Now you have " + tasks.size() + " task(s) in the list");
        storage.write(tasks);
    }
}
