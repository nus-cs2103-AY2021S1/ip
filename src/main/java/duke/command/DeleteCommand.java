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
        List<Task> deletedTasks = taskList.deleteTask(args.trim().split(" "));
        storage.save(taskList);

        StringBuilder string = new StringBuilder("Alright! Deleting these:");
        for (Task task : deletedTasks) {
            string.append("\n").append(task.toString());
        }
        return string.toString();
    }
}
