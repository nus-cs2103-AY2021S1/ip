/**
 * Class that makes a ListCommand which genereates the list of tasks.
 */
public class ListCommand extends Command {
    private String input;

    /**
     * Constructs a ListCommand object with a provided input.
     *
     * @param input The input that has been input by the user.
     */
    ListCommand(String input) {
        this.input = input;
    }

    /**
     * Generates the list of tasks.
     *
     * @param tasklist The list of tasks.
     * @param ui       The ui object that helps generate the different messgaes for display.
     * @return String The message that is to be output on the GUI.
     * @throws DukeEmptyTaskListException If there is an empty tasklist.
     */
    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeEmptyTaskListException {
        String message = "";
        try {
            if (tasklist.numOfTasks() < 1) {
                throw new DukeEmptyTaskListException(input);
            }
            message = ui.printListOfTasks(tasklist.getTasks());
        } catch (DukeEmptyTaskListException e) {
            message = e.getMessage();
        }
        return message;
    }
}
