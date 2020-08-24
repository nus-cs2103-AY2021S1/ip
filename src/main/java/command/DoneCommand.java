package command;

import task.Task;
import util.Storage;
import util.TaskList;
import util.Ui;

import java.io.IOException;

public class DoneCommand extends Command {
    private final int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList lst, Ui ui, Storage storage) {
        int lineNum = taskNum - 1;
        Task task = lst.get(lineNum);
        task.markAsDone();
        try {
            storage.modifyLine(lineNum);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
        ui.showDoneTask(task, taskNum);
    }
}
