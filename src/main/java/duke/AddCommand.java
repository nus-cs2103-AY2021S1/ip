package duke;

/**
 * Represents an add command to add a task into the current list of stored tasks.
 */
public class AddCommand extends Command {

    private CommandType commandType;
    private String description;
    private String dateTime;

    /**
     * Initializes an add command for a task with a description.
     *
     * @param commandType The type of add command to be executed.
     * @param description The description of the task to be added.
     */
    public AddCommand(CommandType commandType, String description) {
        this.commandType = commandType;
        this.description = description;
    }

    /**
     * Initializes an add command for a task with a description and date and time details.
     * This is an overloaded constructor to allow for tasks with date and time details.
     *
     * @param commandType The type of add command to be executed.
     * @param description The description of the task to be added.
     * @param dateTime    Task time and/or date information.
     */
    public AddCommand(CommandType commandType, String description, String dateTime) {
        this.commandType = commandType;
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Adds the task into the task list, then print the add message and updated the added task into storage.
     *
     * @param taskList The existing task list.
     * @param ui       The UI instance which handles Duke's user interface.
     * @param storage  The existing storage for Duke.
     * @throws DukeException When an error occurs when adding the task.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task newTask = null;
        if (commandType == CommandType.TODO) {
            newTask = new ToDo(description);
        } else if (commandType == CommandType.DEADLINE) {
            newTask = new Deadline(description, dateTime);
        } else if (commandType == CommandType.EVENT) {
            newTask = new Event(description, dateTime);
        } else {
            assert false;
        }
        taskList.addTask(newTask);
        ui.processAddMessage(newTask, taskList.getCount());
        storage.updateTasks(taskList);
    }

    /**
     * Returns if the program should continue running at the current point in time.
     * If not, the program should be exited.
     *
     * @return If the program should continue running.
     */
    @Override
    public boolean isInProgram() {
        return true;
    }
}
