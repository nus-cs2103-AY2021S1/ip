package duke.command;

import duke.command.Command;
import duke.exception.InvalidTaskException;
import duke.exception.StorageException;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList list) throws InvalidTaskException, StorageException {
        list.deleteTask(index);
    }
}
