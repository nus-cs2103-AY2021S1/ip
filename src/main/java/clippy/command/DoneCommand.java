package clippy.command;

import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

public class DoneCommand extends Command {
    private int indexOfDoneTask;
    
    public DoneCommand(int indexOfDoneTask) {
        this.indexOfDoneTask = indexOfDoneTask;
    }
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task doneTask = tasks.getTask(indexOfDoneTask);
        doneTask.markAsDone();
        String output = ui.showDone(doneTask);
        
        storage.save(tasks);
        
        return output;
    }

}
