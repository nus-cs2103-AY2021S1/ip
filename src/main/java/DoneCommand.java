public class DoneCommand extends Command {
    int indexOfDoneTask;
    
    DoneCommand(int indexOfDoneTask) {
        this.indexOfDoneTask = indexOfDoneTask;
    }
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        Task doneTask = tasks.getTask(indexOfDoneTask);
        doneTask.markAsDone();
        ui.showDone(doneTask);
        
        storage.save(tasks);
    }
    
    /*
    private void markAsDone(int indexOfDoneTask) {
        Task doneTask = tasks.get(indexOfDoneTask - 1);
        doneTask.markAsDone();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done:\n" +
                "       " + doneTask + "\n" +
                "    ____________________________________________________________");
        writeSaveFile();
    }
     */

    @Override
    boolean isExit() {
        return false;
    }
}
