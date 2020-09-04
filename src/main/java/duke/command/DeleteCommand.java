package duke.command;

import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidIndexException;
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
        this.isExit = false;
    }

    /**
     * deletes the task specified by the user and reflect this change in the storage.
     * finally, the method returns a message indicating that the operation was successful
     * @param tasks the list of tasks
     * @param ui the user interface object responsible for system related commands
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating the deletion of the task was successful
     * @throws InvalidIndexException if the given task number does not exist in the list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        int taskNumber = Integer.parseInt(fullCommand.substring(7));
        Task task = tasks.delete(taskNumber);
        storage.save(tasks);
        return deletedTaskMessage(task, tasks);
    }

    private String deletedTaskMessage(Task deletedTask, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("sure thing. i have removed this task: \n    ")
                .append(deletedTask).append("\n")
                .append(tasks.numberOfTasks());
        return sb.toString();
    }
}
