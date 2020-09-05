package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Command to mark a task in the taskList as done
 */
public class DoneCommand extends Command {
    
    private final int index;

    /**
     * Constructor for the class
     * 
     * @param index index of task in the list to be marked done
     */
    public DoneCommand (int index) {
        assert index >= 0;
        this.index = index;
    }
    
    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {
        Task doneTask = taskList.getTask(index);
        taskList.setDone(index);
        ui.doneMessage(doneTask);
        storage.writeDataToFile(taskList);
    }
}
