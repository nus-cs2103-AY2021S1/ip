package duke.command;

import java.util.List;

import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.InvalidTaskIndexException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates the logic for deleting tasks.
 */
public class DeleteCommand extends Command {
    private static final String DELIMITER = " ";

    /**
     * Constructs a DeleteCommand.
     *
     * @param args Arguments for the command.
     */
    public DeleteCommand(String args) {
        super(args);
    }

    /**
     * Deletes one or more tasks.
     * This may cause the numbering of the list to no longer be contiguous. This is intentional.
     *
     * @param taskList The taskList to operate with.
     * @param storage The storage to operate with.
     * @return A list of tasks that are now deleted.
     * @throws InvalidTaskIndexException If one or more indexes are invalid.
     * @throws StorageException If the file could not be saved after the operation.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws InvalidTaskIndexException, StorageException {
        List<Task> deletedTasks = taskList.deleteTask(args.trim().split(DELIMITER));
        storage.save(taskList);

        StringBuilder string = new StringBuilder("Alright! Deleting these:");
        for (Task task : deletedTasks) {
            string.append("\n").append(task.toString());
        }
        return string.toString();
    }
}
