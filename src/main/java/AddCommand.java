public class AddCommand extends Command {
    private Task task;
    
    public AddCommand(Task task) {
        this.task = task;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        ui.showAddMessage(this.task, tasks);
        storage.writeFile(tasks);
    }
    
    public boolean isExit() {
        return false;
    }
    
}
