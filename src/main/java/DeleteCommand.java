/**
 * Class that makes a delete command which helps with deleting of tasks.
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * Constructs a DeleteCommand object with a provided input.
     *
     * @param input The input that has been input by the user.
     */
    DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Deletes a task.
     *
     * @param tasklist The list of tasks.
     * @param ui       The ui object that helps generate the different messgaes for display.
     * @return String The message that is to be output on the GUI.
     * @throws DukeDeleteException If there is a wrong delete number keyed in.
     */
    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeDeleteException {
        String message = "";
        try {
            int deleteTaskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            if (deleteTaskNumber + 1 > tasklist.numOfTasks() || deleteTaskNumber < 0) {
                throw new DukeDeleteException(input);
            }
            message = ui.printDeleteMessage(tasklist.getTask(deleteTaskNumber), tasklist.numOfTasks() - 1);
            tasklist.deleteTask(deleteTaskNumber);
        } catch (DukeDeleteException e) {
            message = e.getMessage();
        }
        return message;
    }
}
