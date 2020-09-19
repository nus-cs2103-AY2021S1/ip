package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private int taskIndex;
    public DoneCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        Task currTask = tasks.get(this.taskIndex - 1);
        currTask.markAsDone();
        storage.saveData(tasks.getTasks());
        ui.markAsDone(this.taskIndex - 1, tasks);
    }

}
