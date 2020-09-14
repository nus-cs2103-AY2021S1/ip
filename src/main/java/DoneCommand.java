/**
 * Class that makes a DoneCommand which marks the tasks as done.
 */
public class DoneCommand extends Command {
    private String input;

    /**
     * Constructs a DeleteCommand object with a provided input.
     *
     * @param input The input that has been input by the user.
     */
    DoneCommand(String input) {
        this.input = input;
    }

    /**
     * Marks the task as done.
     *
     * @param tasklist The list of tasks.
     * @param ui       The ui object that helps generate the different messgaes for display.
     * @return String The message that is to be output on the GUI.
     * @throws DukeInvalidDoneNumException If an invalid task number is keyed in.
     * @throws DukeEmptyDoneNumException   If there is no done number keyed in.
     */
    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeInvalidDoneNumException, DukeEmptyDoneNumException {
        String message = "";
        try {
            String[] splitMessage = input.split(" ");
            if (splitMessage.length == 1) {
                throw new DukeEmptyDoneNumException(input);
            }
            int doneTaskNumber = Integer.parseInt(splitMessage[1]) - 1;
            if (doneTaskNumber + 1 > tasklist.numOfTasks() || doneTaskNumber < 0) {
                throw new DukeInvalidDoneNumException(input);
            }
            message = tasklist.markAsDone(doneTaskNumber);
        } catch (DukeInvalidDoneNumException e) {
            message = e.getMessage();
        }
        return message;
    }

}
