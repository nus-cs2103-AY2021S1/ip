package duckie.command;

import java.lang.reflect.Array;
import java.util.ArrayList;

import duckie.task.*;
import duckie.exception.*;
import duckie.Ui;
import duckie.Storage;

public class DoneCommand extends Command {
    private int ind;

    public DoneCommand(int ind) {
        this.ind = ind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        ArrayList<Task> lst = tasks.getTaskList();
        if (lst.size() == 0) {
            throw new DuckieNoListException();
        }

        if (lst.size() < ind) {
            throw new DuckieNoIndexException();
        }

        tasks.markTaskDone(this.ind);
        Task task = tasks.getTaskList().get(this.ind - 1);
        try {
            storage.saveToFile(tasks.getTaskList());
        } catch (DuckieException e) {
            throw e;
        }
        ui.checkTaskReply(task);
    }
}
