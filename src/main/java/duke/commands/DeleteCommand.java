package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final boolean HAS_FINISHED = false;
    private int indexOfTaskToBeDeleted;

    public DeleteCommand(int indexOfTaskToBeDeleted) {
        this.indexOfTaskToBeDeleted = indexOfTaskToBeDeleted;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task targetTask = taskList.get(indexOfTaskToBeDeleted - 1);
        taskList.remove(indexOfTaskToBeDeleted - 1);
        try {
            storage.modifyLineInTextFile(indexOfTaskToBeDeleted, "delete");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int size = taskList.getSize();
        ui.printTaskDeleted(targetTask.toString(), size);
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
