public class DoneCommand extends Command {
    protected int indexOfDoneTask;
    
    DoneCommand(int indexOfDoneTask) {
        this.indexOfDoneTask = indexOfDoneTask;
    }
    
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        Task doneTask = tasks.getTask(indexOfDoneTask);
        doneTask.markAsDone();
        String output = ui.showDone(doneTask);
        
        storage.save(tasks);
        
        return output;
    }

    @Override
    boolean isExit() {
        return false;
    }
}
