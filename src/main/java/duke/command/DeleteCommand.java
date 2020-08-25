package duke.command;

import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.InvalidTaskIndexException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates the logic for deleting tasks.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand.
     *
     * @param args Arguments for the command.
     */
    public DeleteCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws InvalidTaskIndexException, StorageException {
        Task deletedTask = taskList.deleteTask(args);
        storage.save(taskList);
        return "Alright! Deleting this:\n" + args + ". " + deletedTask.toString();
    }
}
