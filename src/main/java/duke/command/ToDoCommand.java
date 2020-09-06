package duke.command;

import duke.exception.DukeFileLoadingErrorException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.message.Message;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * represents a command to add a to do task
 */
public class ToDoCommand extends AddTaskCommand {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public ToDoCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * creates a new deadline task which is added to the task list.
     * this change is reflected in the storage.
     * finally, the method returns a message indicating that the operation was successful
     * @param tasks the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating the addition of the deadline task was successful
     * @throws EmptyDescriptionException if the description for the deadline task is empty
     * @throws DukeFileLoadingErrorException if there was an error loading the file
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws EmptyDescriptionException, DukeFileLoadingErrorException {
        Task addedTask = tasks.addToDo(fullCommand);
        storage.save(tasks.getTasks());
        return new CommandResult(Message.addedTaskMessage(addedTask, tasks));
    }
}
