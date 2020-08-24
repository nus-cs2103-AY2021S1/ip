package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to add new Todo.
 */
public class AddTodoCommand extends Command {

    /** Description of Todo to be added */
    private final String description;

    /**
     * Constructs a <code>AddTodoCommand</code> object.
     *
     * @param description Description of Todo to be added.
     */
    public AddTodoCommand(String description) {
        super(false);
        this.description = description;
    }

    /**
     * Adds a Todo to the TaskList and notify the user if successful.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     * @return Nothing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.save(tasks);
        ui.printMessage("Got it. I've added this todo: \n\t   "
                + task + "\n\t "
                + "Now you have "
                + getTaskDescription(tasks.getNumberOfTask())
                + " in the list.");
    }

    /**
     * Returns a String description of the number of Task.
     *
     * @param noOfTask Number of Task in TaskList
     * @return String description of the number of Task.
     */
    public static String getTaskDescription(int noOfTask) {
        String taskDescription = "";
        if (noOfTask > 1) {
            taskDescription = noOfTask + " tasks";
        } else {
            taskDescription = noOfTask + " task";
        }
        return taskDescription;
    }
}
