package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class RemoveTaskCommand extends Command {
    
    private final int index;
    
    public RemoveTaskCommand (int index) {
        this.index = index;
    }
    
    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {
        Task removedTask = taskList.get(index);         
        taskList.removeTask(index);
        ui.removeTaskMessage(removedTask, taskList.size());
        storage.writeToDataFile(taskList);
    }
}
