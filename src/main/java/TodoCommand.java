/**
 * TodoCommand class executes todo actions.
 * Extends from Command class.
 */
public class TodoCommand extends Command {
    /**
     * Creates a todo object.
     * @param ui ui that handles
     * @param taskList list of tasks
     * @param args String of todo commands
     */
    public TodoCommand(Ui ui, TaskList taskList, String args) {
        super(ui, taskList, args);
    }

    /**
     * Executes actions for the todo command
     * @return String containing a successful add message
     */
    @Override
    public String action() {
        String result;
        try {
            if (args.equals("")) {
                throw new DukeException("The description of a todo cannot be empty!");
            } else {
                String description = args;
                Task todo = new ToDos(description);
                taskList.addTask(todo);
                result = ui.printAdd(taskList, todo);
            }
        } catch (DukeException e) {
            result = ui.printDukeError(e);
        }
        return result;
    }
}
