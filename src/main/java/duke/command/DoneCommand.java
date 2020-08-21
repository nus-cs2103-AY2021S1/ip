package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {

    int index;

    public DoneCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(index);
        task.markAsDone();
        ui.printDoneTask(task);
        storage.replace(task);
    }
}
