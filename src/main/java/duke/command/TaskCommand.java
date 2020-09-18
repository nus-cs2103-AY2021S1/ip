package duke.command;

import duke.ImageType;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.exception.WrongFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskType;
import duke.task.Todo;

/**
 * Represents a TaskCommand.
 */
public class TaskCommand extends Command {

    /**
     * Type of task.
     */
    protected TaskType taskType;

    /**
     * Description of task.
     */
    protected String description;

    /**
     * Creates a TaskCommand object.
     * @param taskType Type of task.
     * @param description Description of task.
     */
    public TaskCommand(TaskType taskType, String description) {
        super(CommandType.TASK, ImageType.TICK);
        this.taskType = taskType;
        this.description = description;
    }

    /**
     * Executes an add task command
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return Acknowledgement that a task has been added.
     * @throws EmptyDescriptionException For when the user did not give a description.
     * @throws WrongFormatException For when the description does not fit the format of the task type.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) throws EmptyDescriptionException,
            WrongFormatException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        try {
            switch (taskType) {
            case TODO:
                taskList.addTask(new Todo(description, taskType));
                break;
            case DEADLINE:
                String[] deadlineSplit = description.split(",");
                taskList.addTask(new Deadline(deadlineSplit[0], deadlineSplit[1], taskType));
                break;
            case EVENT:
                String[] eventSplit = description.split(",");
                taskList.addTask(new Event(eventSplit[0], eventSplit[1], taskType));
                break;
            default:
            }
            Storage.saveTaskChanges(taskList);
            return ui.printAddAcknowledgement(taskList) + ui.printAdditionActionMessage();
        } catch (IndexOutOfBoundsException e) {
            throw new WrongFormatException();
        }
    }
}
