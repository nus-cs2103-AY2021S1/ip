/**
 * Class to run the todo command.
 */
public class ToDoCommand implements Command {

    public final String DESCRIPTION;

    /**
     * constructor
     */
    public ToDoCommand(String description) {
        this.DESCRIPTION = description;
    }

    /**
     * Executes the todo command, causing add a task of type todo to the taskList,
     * provided that the command input is valid.
     *  @param taskList Used by Duke to keep track of tasks.
     * @param ui Responsible for printing to console after execution.
     * @param storage Stores tasks in a text format.
     * @return
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
            ToDo t = new ToDo(DESCRIPTION, false);
            taskList.addTask(t);
            storage.write(taskList.TASKS);
            String result = ui.showLine();
            result += "\n" + ui.createToDoSuccessMessage(t, taskList.TASKS.size());
            result += ui.showLine();
        return result;
    }

    /**
     * Returns true if a bye command is called.
     * Returns False otherwise.
     *
     * @return boolean indicating whether Duke is to stop running.
     */
    public boolean isExit() {
        return false;
    }
}
