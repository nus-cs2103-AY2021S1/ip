public class AddCommand extends Command {
    private Task task;
    
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PandaBotException {
        // add the task
        tasks.addTask(task);
        
        // print
        ui.printOnAddTask(task, tasks);
        
        // save
        storage.write(tasks.getTaskList());
    }
}
