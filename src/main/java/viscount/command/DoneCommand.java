package viscount.command;

import viscount.*;
import viscount.exception.ViscountException;

public class DoneCommand extends Command {
    private int taskIndex;
    
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ViscountException {
        tasks.markDone(taskIndex);
        storage.saveToDisk(tasks.getTasks());
        ui.showDone(tasks.getTask(taskIndex));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
