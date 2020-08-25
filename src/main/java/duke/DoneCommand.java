package duke;

import java.io.IOException;

/**
 * Command invoked when a task is called to be marked as done
 */
public class DoneCommand extends Command{
    int taskIndex;

    /**
     * Constructor
     * @param taskIndex Integer indicating the index of task to be marked as done
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks task as done, prints output, saves changes to hard disk
     * @param tasks TaskList containing Tasks
     * @param ui Ui object that handles printing of any necessary output
     * @param storage Storage object that handles saving Tasks to hard disk
     * @throws DukeException
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task t = tasks.doneTask(taskIndex);

        ui.printDivider();
        ui.printMsg("Mr Camel will mark this task as done:\n");
        ui.printMsg("\t" + t);
        ui.printDivider();

        super.execute(tasks, ui, storage);
    }
}
