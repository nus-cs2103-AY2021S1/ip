package duke.command;

import duke.Output;
import duke.exception.DukeFileLoadingErrorException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * represents a command to add a task
 */
public abstract class AddTaskCommand extends Command {
    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    AddTaskCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    /**
     * creates a new deadline task which is added to the task list.
     * this change is reflected in the storage.
     * finally, the method returns a message indicating that the operation was successful
     * @param tasks the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating the addition of the deadline task was successful
     * @throws EmptyDescriptionException if the description for the deadline task is empty
     * @throws EmptyDateException if the date for the deadline task is empty
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws EmptyDescriptionException, EmptyDateException, DukeFileLoadingErrorException {
        Task addedTask = tasks.add(fullCommand);
        storage.save(tasks.getTasks());
        return new CommandResult(Output.addedTaskMessage(addedTask, tasks));
    }
}
