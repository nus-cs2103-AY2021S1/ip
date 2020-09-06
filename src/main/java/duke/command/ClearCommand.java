package duke.command;

import duke.Output;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * represents a command to clear the entire list of tasks
 */
public class ClearCommand extends Command {

    /**
     * class constructor
     */
    public ClearCommand() {
        super("clear");
        this.isExit = false;
    }

    /**
     * deletes all of the tasks in the given task list and updates this change to the storage
     * finally, the method returns a message indicating the operation was successful
     * @param tasks the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating that the task list was successfully cleared
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        tasks.deleteAll();
        storage.save(tasks.getTasks());
        return new CommandResult(Output.clearedAllTasksMessage());
    }
}
