package duke.command;

import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.Task;
import duke.task.TaskException;
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
    public CompleteCommand(String args){
        super(args);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskException, StorageException {
        Task completedTask = taskList.completeTask(args);
        storage.save(taskList);
        return "Neat! Marking this as complete:\n" + args + ". " + completedTask.toString();
    }
}
