package Command;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class DeleteCommand extends Command {
    int taskPosition;

    public DeleteCommand(int position) {
        this.taskPosition = position;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskPosition < taskList.getTaskList().size() && taskPosition >= 0) {
            taskList.getTaskList().remove(taskPosition);
            storage.updateStorage(taskList);
            ui.showDeleted(taskList.getTaskList().get(taskPosition));
        } else {
            throw new DukeException("☹ OOPS !!! ¡Esta tarea aún no existe!");
        }
    }
}
