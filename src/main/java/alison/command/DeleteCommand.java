package alison.command;

import alison.exception.AlisonException;
import alison.task.Task;
import alison.tool.*;

public class DeleteCommand extends Command {

    public int index;

    public DeleteCommand(int i) {
        index = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlisonException {
        try {
            Task removedTask = tasks.remove(index - 1);
            ui.removeTaskMsg(removedTask, tasks);
            storage.update(tasks);
        } catch (Exception e) {
            throw AlisonException.invalidIndexException();
        }
    }

}
