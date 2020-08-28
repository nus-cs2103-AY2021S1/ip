public class CompleteCommand extends Command {
    private int number;
    
    public CompleteCommand(int number) {
        this.number = number;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        Task task = taskList.get(number);
        task.completeTask();
        ui.showCompletion(task);
        store.write(taskList);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
