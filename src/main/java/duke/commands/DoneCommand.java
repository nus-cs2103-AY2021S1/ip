package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class DoneCommand extends Command {
    private final boolean HAS_FINISHED = false;
    private int indexOfTaskToBeDone;

    public DoneCommand(int indexOfTaskToBeDone) {
        this.indexOfTaskToBeDone = indexOfTaskToBeDone;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task targetTask = taskList.get(indexOfTaskToBeDone - 1);
        String message = targetTask.completeTask();
        try {
            storage.modifyLineInTextFile(indexOfTaskToBeDone, "done");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.printDone(message);
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
