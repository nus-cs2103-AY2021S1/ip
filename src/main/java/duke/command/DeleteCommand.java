package duke.command;

import duke.Exception.DukeException;
import duke.Exception.TaskNotFoundException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Task;

/**
 * Represents a deleting {@link duke.task.Task} command.
 * @author Tee Kok Siang
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param taskNumber Task number of the deleting task.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes a DeleteCommand to delete a task.
     * Deletes the task and update the file in the hard disk.
     * Displays feedback message.
     *
     * @param taskList List of tasks.
     * @param ui UI to handle user interaction.
     * @param storage Storage to save the task list in the hard disk.
     * @return Formatted response message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNumber > taskList.size() || taskNumber < 1) {
            throw new TaskNotFoundException();
        }
        Task deletedTask = taskList.getTask(taskNumber);
        taskList.deleteTask(taskNumber);
        // display delete task success message
        String response = "Noted. I've removed this task:";
        response += "\n\t\t".concat(deletedTask.toString());
        response += String.format("\n\tNow you have %d tasks in the list.", taskList.size());
        ui.printResponse(response);
        // update task data in the file
        storage.writeFile(taskList);
        return response;
    }
}
