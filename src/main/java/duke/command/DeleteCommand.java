package duke.command;

import duke.ui.Ui;
import duke.exception.DukeOutOfBoundsException;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand implements Command {
    private int index;
    
    public DeleteCommand(int index) {
        this.index = index;
    }
    
    private void checkIndex(TaskList tasks) throws DukeOutOfBoundsException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeOutOfBoundsException(CommandKey.DELETE.getKey() + " " + index);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            checkIndex(tasks);
            Task removedTask = tasks.delete(index);
            ui.displayDeletedTaskMessage(removedTask, tasks.size());
        } catch (DukeOutOfBoundsException e) {
            ui.displayError(e.toString());
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
