package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private int index;
    
    public DoneCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.markAsDone(index);
    }
}
