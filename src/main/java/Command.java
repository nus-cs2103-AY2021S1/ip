/**
 * Command class is an abstract class which executes an action
 */
public abstract class Command {
    protected Ui ui;
    protected TaskList taskList;
    protected String args;

    /**
     * Creates a Command object
     * @param ui Ui that handles the user interface
     * @param taskList list of tasks
     * @param args arguments of the command
     */
    public Command(Ui ui, TaskList taskList, String args) {
        this.ui = ui;
        this.taskList = taskList;
        this.args = args;
    }

    public abstract String action();

}
