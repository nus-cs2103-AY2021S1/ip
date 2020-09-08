package duke.command;

import duke.Exception.DukeException;
import duke.Exception.InvalidPriorityLevel;
import duke.Exception.TaskNotFoundException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a prioritising {@link duke.task.Task} command.
 * @author Tee Kok Siang
 */
public class PriorityCommand extends Command {
    private final int taskNumber;
    private final int priorityLevel;
    /** Number of word count for a PriorityCommand */
    public static final int WORD_COUNT = 3;
    /** Indicates the position of priority level in the command */
    public static final int PRIORITY_LEVEL_POSITION = 2;

    /**
     * Constructs a DoneCommand object.
     *
     * @param taskNumber Task number of the done task.
     */
    public PriorityCommand(int taskNumber, int priorityLevel) {
        this.taskNumber = taskNumber;
        this.priorityLevel = priorityLevel;
    }

    /**
     * Executes a PriorityCommand to mark task's priority level.
     * Marks task's priority level and update the file in the hard disk.
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
        boolean isPriorityValid = priorityLevel == Task.PRIORITY_LOW
                || priorityLevel == Task.PRIORITY_MEDIUM
                || priorityLevel == Task.PRIORITY_HIGH;
        if (!isPriorityValid) {
            throw new InvalidPriorityLevel();
        }
        taskList.priorityTask(taskNumber, priorityLevel);
        // display update task success message
        String response = "Nice! I've update the task priority: " + Task.Priority.get(priorityLevel);
        response += "\n\t\t".concat(taskList.getTask(taskNumber).toString());
        ui.printResponse(response);
        // update task data in the file
        storage.writeFile(taskList);
        return response;
    }
}
