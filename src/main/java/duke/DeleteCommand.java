package duke;

import java.io.IOException;

/**
 * Carries out the deletion of tasks from tasklist, and saves the changes to hard disk
 */
public class DeleteCommand extends Command {
    int taskIndex;

    /**
     * Constructor
     * @param taskIndex Integer indicating the index of task to be deleted
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Prints output of task deletion as well as delete the task, thereafter saving to hard disk
     * @param tasks TaskList containing Tasks
     * @param ui Ui object that handles printing of any necessary output
     * @param storage Storage object that handles saving Tasks to hard disk
     * @throws DukeException
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task t = tasks.deleteTask(taskIndex);

        ui.printDivider();
        ui.printMsg("Mr Camel will delete this task:\n");
        ui.printMsg("\t" + t);
        ui.printMsg("Number of tasks: " + tasks.getTasklist().size());
        ui.printDivider();

        super.execute(tasks, ui, storage);
    }
}
