package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeInvalidIndexException;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand implements Command {
    private final int index; //0 to tasks.getSize() - 1

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.deleteTask(index);
            storage.save(tasks);
            ui.showDeleted(task, tasks.getSize());
        } catch (DukeInvalidIndexException e) {
            ui.showError(e);
        } catch (IOException e) {
            ui.showError(e);
        }
        return true;
    }
}
