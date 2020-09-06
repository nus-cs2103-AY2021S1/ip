package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String number) throws DukeException {
        try {
            this.index = Integer.parseInt(number) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry, I can't seem to find the task...");
        }
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        try{
            Task task = list.getList().get(index);
            assert index >= 0 : "Index out of bound";
            list.delete(task);
            storage.generateTxt(list);
            return ui.showDelete(task, list);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, I can't seem to find the task...");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
