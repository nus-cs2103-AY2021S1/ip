package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

public class AddCommand extends Command {

    private Task task;

    public AddCommand (Task t) {
        this.task = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.add(this.task);
        ui.showAddTask(this.task, tasks.size());
    }
}
