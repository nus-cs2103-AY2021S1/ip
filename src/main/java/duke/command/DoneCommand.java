package duke.command;

import duke.Exception.DukeException;
import duke.Exception.TaskNotFoundException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Represents a completing {@link duke.task.Task} command.
 * @author Tee Kok Siang
 */
public class DoneCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a DoneCommand object.
     *
     * @param taskNumber Task number of the done task.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes a DoneCommand to mark task as done.
     * Marks task as done and update the file in the hard disk.
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
        taskList.doneTask(taskNumber);
        // display update task success message
        String response = "Nice! I've marked this task as done:";
        response += "\n\t\t".concat(taskList.getTask(taskNumber).toString());
        ui.printResponse(response);
        // update task data in the file
        storage.writeFile(taskList);
        return response;
    }
}
