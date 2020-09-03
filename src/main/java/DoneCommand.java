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
