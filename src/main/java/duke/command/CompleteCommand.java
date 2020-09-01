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

    /**
     * Constructs a CompleteCommand.
     *
     * @param args Arguments for the command.
     */
    public CompleteCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws InvalidTaskIndexException, StorageException {
        List<Task> completedTasks = taskList.completeTasks(args.trim().split(" "));
        storage.save(taskList);

        StringBuilder string = new StringBuilder("Neat! Marking these as complete:");
        for (Task task : completedTasks) {
            string.append("\n").append(task.toString());
        }
        return string.toString();
    }
}
