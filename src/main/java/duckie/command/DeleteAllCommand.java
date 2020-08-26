package duckie.command;

import java.util.ArrayList;

import duckie.task.*;
import duckie.Ui;
import duckie.Storage;
import duckie.exception.*;

public class DeleteAllCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        ArrayList<Task> lst = tasks.getTaskList();
        if (lst.size() == 0) {
            throw new DuckieNoListException();
        }

        tasks.deleteAllTasks();
        try {
            storage.saveToFile(tasks.getTaskList());
        } catch (DuckieException e) {
            throw e;
        }
        ui.deleteAllReply();
    }
}
