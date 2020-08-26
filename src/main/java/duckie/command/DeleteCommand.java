package duckie.command;

import java.util.ArrayList;

import duckie.task.*;
import duckie.Ui;
import duckie.Storage;
import duckie.exception.*;
import duckie.task.*;

public class DeleteCommand extends Command {
    private int ind;

    public DeleteCommand(int ind) {
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

        Task task = lst.get(this.ind - 1);
        tasks.deleteTask(ind - 1);
        try {
            storage.saveToFile(tasks.getTaskList());
        } catch (DuckieException e) {
            throw e;
        }

        ui.deleteTaskReply(task);
    }
}
