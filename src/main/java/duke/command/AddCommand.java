package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents an AddCommand where user wants to add a task.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Creates an AddCommand.
     *
     * @param task Task which is to be added to the list of tasks.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add a task to list of tasks.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTasks(task);
        String echoizer = "\t Got it. I've added this task:\n"
                + "\t %s\n"
                + "\t Now you have %d tasks in the list.\n";
        ui.showAction(String.format(echoizer, task, tasks.numTasks()));
        storage.save(tasks);
    }
}
