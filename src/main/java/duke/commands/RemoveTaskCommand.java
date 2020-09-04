package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class RemoveTaskCommand extends Command {
    
    private int index;
    
    public RemoveTaskCommand (int index) {
        this.index = index;
    }
    
    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {
        if (index > taskList.size() - 1) {
            
        } else {
            Task removedTask = taskList.get(index);
            taskList.removeTask(index);
            ui.removeTaskMessage(removedTask, taskList.size());
            storage.writeToDataFile(taskList);
        }
    }
}
