public abstract class Command {
    private TaskList taskList;
    private Ui ui;
    
    Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }
    
    public abstract String execute();
}
