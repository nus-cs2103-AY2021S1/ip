package duke.command;

import duke.exception.InvalidTaskException;
import duke.exception.StorageException;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList list) throws InvalidTaskException, StorageException {
        list.completeTask(this.taskIndex);
    }
}
