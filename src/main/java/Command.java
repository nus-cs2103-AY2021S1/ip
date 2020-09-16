public abstract class Command {
    protected TaskList taskList;
    protected Ui ui;
    protected String arguments;
    
    Command(TaskList taskList, Ui ui, String arguments) {
        this.taskList = taskList;
        this.ui = ui;
        this.arguments = arguments;
    }
    
    public abstract String execute();
}
