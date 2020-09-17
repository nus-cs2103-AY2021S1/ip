public abstract class Command {
    protected TaskList taskList;
    protected Ui ui;
    protected String arguments;
    
    Command(TaskList taskList, Ui ui, String arguments) {
        this.taskList = taskList;
        this.ui = ui;
        this.arguments = arguments;
    }

    /**
     * Executes the command and returns the applications corresponding output.
     * @return application's corresponding output as a String.
     */
    public abstract String execute();
}
