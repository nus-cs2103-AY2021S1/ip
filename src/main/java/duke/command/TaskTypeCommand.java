package duke.command;

import duke.ImageType;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidTaskTypeException;
import duke.task.TaskType;

/**
 * Represents a TaskTypeCommand.
 */
public class TaskTypeCommand extends Command {

    /**
     * Type of task.
     */
    protected TaskType taskType;

    /**
     * Creates a TaskTypeCommand object.
     * @param taskType Type of task.
     */
    public TaskTypeCommand(TaskType taskType) {
        super(CommandType.TASKTYPE, ImageType.PENDING);
        this.taskType = taskType;
    }

    /**
     * Returns the type of task.
     * @return Type of task.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Executes a task type command
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return Prompt for user to enter a task description including format examples where needed.
     * @throws InvalidTaskTypeException For when the user enters an invalid task type.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) throws InvalidTaskTypeException {
        switch (taskType) {
        case TODO:
            return ui.printPrompt("Please enter the task");
        case DEADLINE:
            return ui.printPrompt("Please enter the task followed by the date and time of the deadline\n"
                    + "e.g., submit report, 11/10/2019 1700\n");
        case EVENT:
            return ui.printPrompt("Please enter the event followed by the date and time of the event\n"
                    + "e.g., team project meeting, 2/10/2019 1400-1600\n");
        default:
            throw new InvalidTaskTypeException();
        }
    }
}
