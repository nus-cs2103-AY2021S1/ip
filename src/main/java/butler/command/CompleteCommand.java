package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;
import java.util.ArrayList;

public class CompleteCommand extends Command {
    private ArrayList<Integer> indexArray;

    public CompleteCommand(ArrayList<Integer> indexArray) {
        this.indexArray = indexArray;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ButlerException {
        for (int i : indexArray) {
            taskList.completeTask(i);
            storage.storeTaskList(taskList);
            ui.showTaskCompleted(i);
        }
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
