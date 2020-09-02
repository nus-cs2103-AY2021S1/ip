package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * @return String displaying completion of Command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        String echoizer = "  Got it. I've added this task:\n"
                + "    %s\n"
                + "  Now you have %d tasks in the list.\n";
        storage.save(tasks);
        return ui.showAction(String.format(echoizer, task, tasks.size()));
    }
}
