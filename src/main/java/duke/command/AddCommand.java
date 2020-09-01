package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;

    /**
     * Initializes AddCommand object.
     *
     * @param task Task to be added to the list.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        try {
            storage.write(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return ui.showAdd(task, tasks);
    }
}
