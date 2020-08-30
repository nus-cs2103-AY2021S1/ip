package duke.command;

import duke.Storage;
import duke.Ui;

import duke.task.Task;
import duke.task.TaskList;

import duke.exception.InvalidTaskException;
import duke.exception.StorageException;

public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList list, Storage storage) throws InvalidTaskException, StorageException {
        Task doneTask = list.completeTask(this.taskIndex);
        storage.writeTaskStorage(list.getSaveString());
        Ui.doneTaskMessage(doneTask);
    }
}
