package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

import java.io.IOException;

public class DoneCommand extends Command {
    private final boolean HAS_FINISHED = false;
    private int indexOfTaskToBeDone;

    public DoneCommand(int indexOfTaskToBeDone) {
        this.indexOfTaskToBeDone = indexOfTaskToBeDone;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) {
        Task targetTask = taskList.get(indexOfTaskToBeDone - 1);
        String message = targetTask.completeTask();
        try {
            storage.modifyLineInTextFile(indexOfTaskToBeDone, "done");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.printDone(message);
        return new CommandResult(message);
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
