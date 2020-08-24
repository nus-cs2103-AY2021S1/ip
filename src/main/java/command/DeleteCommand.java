package command;

import task.Task;
import util.Storage;
import util.TaskList;
import util.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList lst, Ui ui, Storage storage) {
        try {
            int lineNum = taskNum - 1;
            Task task = lst.get(lineNum);
            storage.deleteLine(lineNum);
            lst.remove(task);
            ui.showDeleteTask(task, taskNum);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
