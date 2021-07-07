package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    private int index;

    /**
     * Initializes DoneCommand object.
     *
     * @param index Index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        task.markAsDone();
        try {
            storage.write(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return ui.showDoneTask(task);
    }
}
