public class DeleteCommand extends Command {
    private final int taskNum;
    
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PandaBotException {
        // delete task
        Task t = tasks.getTaskAt(taskNum);
        tasks.deleteTask(taskNum);
        
        // print
        ui.printOnDelete(t, tasks);
        
        // save
        storage.write(tasks.getTaskList());
        
    }
}