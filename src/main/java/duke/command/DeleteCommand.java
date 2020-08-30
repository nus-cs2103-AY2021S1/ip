package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskException;
import duke.exception.StorageException;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Storage storage) throws InvalidTaskException, StorageException {
        Task deletedTask = list.deleteTask(index);
        storage.writeTaskStorage(list.getSaveString());
        Ui.deleteTaskMessage(deletedTask, list.taskListSize());
    }
}
