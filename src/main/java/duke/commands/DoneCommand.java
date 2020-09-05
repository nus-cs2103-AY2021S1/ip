package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class DoneCommand extends Command {
    
    private final int index;
    
    public DoneCommand (int index) {
        assert index >= 0;
        this.index = index;
    }
    
    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {
        Task doneTask = taskList.get(index);
        taskList.setDone(index);
        ui.doneMessage(doneTask);
        storage.writeToDataFile(taskList);
    }
}
