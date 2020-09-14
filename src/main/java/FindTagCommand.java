/**
 * Class that makes a FindTagCommand which finds the tasks with the different tags.
 */
public class FindTagCommand extends Command {
    private String input;

    /**
     * Constructs a FindTagCommand object with a provided input.
     *
     * @param input The input that has been input by the user.
     */
    FindTagCommand(String input) {
        this.input = input;
    }

    /**
     * Finds the task with the input tag provided.
     *
     * @param tasklist The list of tasks.
     * @param ui       The ui object that helps generate the different messgaes for display.
     * @return String The message that is to be output on the GUI.
     * @throws DukeEmptyFindTagException If there is empty message.
     * @throws DukeNoMatchesTagExcpetion If there are no matches to the input.
     */
    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeEmptyFindTagException, DukeNoMatchesTagExcpetion {
        String message = "";
        try {
            String[] findParts = input.split(" ");
            if (findParts.length == 1) {
                throw new DukeEmptyFindTagException(input);
            } else {
                message = ui.printFoundTagsTasks(findParts[1], tasklist.getTasks());
            }
        } catch (DukeEmptyFindTagException e) {
            message = e.getMessage();
        }
        assert tasklist.numOfTasks() > 0;
        return message;
    }
}
