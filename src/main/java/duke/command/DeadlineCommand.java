package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Is responsible for handling commands starting with <code>deadline</code>.
 */
public class DeadlineCommand extends TaskCreationCommand {
    public static final String COMMAND = "deadline";
    public static final String TIME_SPECIFIER = "/by";

    private String description;
    private String time;

    /**
     * Creates a <code>DeadlineCommand</code> object.
     * @param description The description of the task
     * @param time The time by which the task is due
     */
    public DeadlineCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    /**
     * Creates a new <code>Deadline</code> task and add pass it to <code>TaskCreationCommand</code>'s execute method
     * to add the task to the <code>TaskList</code>.
     * @param tasks A list of tasks
     * @param ui An Ui object that correspond to interacting with the user
     * @param storage A database that stores the task list locally when the program is not running
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, time);
        return super.execute(task, ui, tasks);
    }
}
