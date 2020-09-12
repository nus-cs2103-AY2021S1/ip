package duke.command;

import duke.exception.DukeFileLoadingErrorException;
import duke.exception.DukeEmptyDateException;
import duke.exception.DukeEmptyDescriptionException;
import duke.message.Message;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * represents a command to add a deadline task
 */
public class DeadlineCommand extends AddTaskCommand {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * creates a new deadline task which is added to the task list.
     * this change is reflected in the storage.
     * finally, the method returns the command result indicating that the task was successfully added to the task list
     * @param taskList the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return the command result indicating that the task list was successfully cleared
     * @throws DukeEmptyDescriptionException if the description for the deadline task is empty
     * @throws DukeEmptyDateException if the date for the deadline task is empty
     * @throws DukeFileLoadingErrorException if there was an error loading the file
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) throws DukeEmptyDescriptionException, DukeEmptyDateException, DukeFileLoadingErrorException {
        Task addedTask = taskList.addDeadline(fullCommand);
        storage.save(taskList.getTasks());
        return new CommandResult(Message.addedTaskMessage(addedTask, taskList));
    }
}
