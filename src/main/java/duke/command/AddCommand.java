package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand implements Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task to the given list.
     *
     * @param tasks current list of tasks which will be added to
     * @param ui user interface to show messages
     * @param storage storage interface to write the entire TaskList to file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.print("Got it. I've added this task:", "\t" + task,
                         "Now you have " + tasks.size() + " task(s) in the list");
        storage.write(tasks);
    }
}
