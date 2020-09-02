package duke;

public class CompleteCommand extends Command {
    private int number;
    
    public CompleteCommand(int number) {
        this.number = number;
    }
    
    @Override
    public String execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        Task task = taskList.get(number);
        task.completeTask();
        store.write(taskList);
        return ui.showCompletion(task);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
