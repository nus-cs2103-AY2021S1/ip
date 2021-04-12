package duke.command;

import java.util.List;

import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.InvalidTaskIndexException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates the logic for completing tasks.
 */
public class CompleteCommand extends Command {
    private static final String DELIMITER = " ";

    /**
     * Constructs a CompleteCommand.
     *
     * @param args Arguments for the command.
     */
    public CompleteCommand(String args) {
        super(args);
    }

    /**
     * Marks one or more tasks as completed.
     *
     * @param taskList The taskList to operate with.
     * @param storage The storage to operate with.
     * @return The tasks that are now marked as completed.
     * @throws InvalidTaskIndexException If one or more indexes are invalid.
     * @throws StorageException If the file could not be saved after the operation.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws InvalidTaskIndexException, StorageException {
        List<Task> completedTasks = taskList.completeTasks(args.trim().split(DELIMITER));
        storage.save(taskList);

        StringBuilder string = new StringBuilder("Neat! Marking these as complete:");
        for (Task task : completedTasks) {
            string.append("\n").append(task.toString());
        }
        return string.toString();
    }
}
