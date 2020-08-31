package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;
public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(String number) throws DukeException {
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
            task.markAsDone();
            storage.generateTxt(list);
            return ui.showDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, I can't seem to find the task...");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
