/**
 * Class to run the todo command.
 */
public class ToDoCommand implements Command {

    public final String COMMAND;

    /**
     * constructor
     */
    public ToDoCommand(String command) {
        this.COMMAND = command;
    }

    /**
     * Executes the todo command, causing add a task of type todo to the taskList,
     * provided that the command input is valid.
     *
     * @param taskList Used by Duke to keep track of tasks.
     * @param ui Responsible for printing to console after execution.
     * @param storage Stores tasks in a text format.
     * @throws MissingDescriptionException If the todo command is missing a description.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String description = command.substring(4).trim();
        if (description.length() == 0) {

            throw new MissingDescriptionException();
        } else {
            ToDo t = new ToDo(description, false);
            taskList.addTask(t);
            storage.write(taskList.TASKS);
            ui.showLine();
            ui.createToDoSuccessMessage(t, taskList.TASKS.size());
            ui.showLine();
        }
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
