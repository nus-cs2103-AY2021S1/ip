package duke.command;

import duke.exception.DukeFileLoadingErrorException;
import duke.message.Message;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.exception.DukeInvalidIndexException;
import duke.task.Task;

/**
 * represents a command to deleted the specified task
 */
public class DeleteCommand extends Command {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * deletes the task specified by the user.
     * this change is reflected in the storage.
     * finally, the method returns the command result indicating that the task list was successfully deleted
     * @param taskList the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return the command result indicating that the task list was successfully cleared
     * @throws DukeInvalidIndexException if the given task number does not exist in the list
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) throws DukeInvalidIndexException, DukeFileLoadingErrorException {
        int taskNumber = Integer.parseInt(fullCommand.substring(7));
        Task task = taskList.delete(taskNumber);
        storage.save(taskList.getTasks());
        return new CommandResult(Message.deletedTaskMessage(task, taskList));
    }
}
