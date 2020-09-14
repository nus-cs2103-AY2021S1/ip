package duke.commands;

import duke.Storage;
import duke.lists.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Command to mark a task in the taskList as done
 */
public class DoneCommand extends Command {
    
    private final int index;
    private final boolean isDone;

    /**
     * Constructor for the class
     * 
     * @param index index of task in the list to be marked done
     */
    public DoneCommand (int index, boolean isDone) {
        assert index >= 0;
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {
        Task doneTask = taskList.getTask(index);
        taskList.setDone(index, isDone);
        ui.doneMessage(doneTask);
        storage.writeDataToFile(taskList);
    }
    
    @Override
    public String toString() {
        return "DoneCommand " + index;
    }
}
