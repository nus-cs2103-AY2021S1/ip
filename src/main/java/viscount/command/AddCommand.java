package viscount.command;

import java.time.LocalDateTime;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountSaveDataException;
import viscount.task.Deadline;
import viscount.task.Event;
import viscount.task.Task;
import viscount.task.TaskType;
import viscount.task.Todo;


/**
 * Represents an add command.
 */
public class AddCommand extends Command {
    private TaskType taskType;
    private String description;
    private LocalDateTime dateTime;

    /**
     * Instantiates a new add command
     *
     * @param taskType Type of task added
     * @param description Description of task added
     * @param dateTime Date and time of task added
     */
    public AddCommand(TaskType taskType, String description, LocalDateTime dateTime) {
        this.taskType = taskType;
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Executes the add command and returns the response from Viscount.
     *
     * @param taskList Task list where tasks are stored.
     * @param ui Ui to display response.
     * @param storage Storage to save changes to disk.
     * @return The response from Viscount.
     * @throws ViscountSaveDataException If exception occurs with writing to disk.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage) throws ViscountSaveDataException {
        Task newTask = getNewTask();
        taskList.add(newTask);
        storage.saveToDisk(taskList.getTasks());
        return ui.getAddResponse(newTask, taskList.getTasksSize());
    }

    /**
     * Gets a new task to be added.
     *
     * @return A new task to be added.
     */
    private Task getNewTask() {
        Task newTask = null;

        switch(taskType) {
        case TODO:
            newTask = new Todo(description, false);
            break;
        case DEADLINE:
            newTask = new Deadline(description, false, dateTime);
            break;
        case EVENT:
            newTask = new Event(description, false, dateTime);
            break;
        default:
            break;
        }

        return newTask;
    }

    /**
     * Compares this command with another object for equality.
     *
     * @param o Object compared.
     * @return True if this command is equal to the object, and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        AddCommand addCommand = (AddCommand) o;
        boolean hasSameTaskType = this.taskType.equals(addCommand.taskType);
        boolean hasSameDescription = this.description.equals(addCommand.description);
        boolean hasSameDateTime = (this.dateTime == null)
                ? (addCommand.dateTime == null)
                : (addCommand.dateTime != null) && this.dateTime.isEqual(addCommand.dateTime);
        return hasSameTaskType && hasSameDescription && hasSameDateTime;
    }
}
