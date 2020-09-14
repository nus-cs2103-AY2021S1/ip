package duke.commands;

import duke.Storage;
import duke.lists.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Command to remove a task from the taskList
 */
public class RemoveTaskCommand extends Command {
    
    private final int index;

    /**
     * Constructor for the class
     * 
     * @param index index of task in the list to be removed
     */
    public RemoveTaskCommand (int index) {
        assert index >= 0;
        this.index = index;
    }

    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {
        Task removedTask = taskList.getTask(index);         
        taskList.removeTask(index);
        ui.removeTaskMessage(removedTask, taskList.size());
        storage.writeDataToFile(taskList);
    }
    
    @Override
    public String toString() {
        return "RemoveTaskCommand " + index;
    }
}
