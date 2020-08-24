package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeInvalidIndexException;
import duke.task.Task;
import duke.TaskList;

import java.io.IOException;

public class AddCommand implements Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(this.task);
            storage.save(tasks);
            ui.showAdded(this.task, tasks.getSize());
        } catch (IOException | DukeInvalidIndexException e) {
            ui.showError(e);
        }
        return true;
    }
}
