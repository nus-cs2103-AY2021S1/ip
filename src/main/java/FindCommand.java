/**
 * Class that makes a Findcommand which helps find the different tasks.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * Constructs a FindCommand object with a provided input.
     *
     * @param input The input that has been input by the user.
     */
    FindCommand(String input) {
        this.input = input;
    }

    /**
     * Finds the task with the input provided.
     *
     * @param tasklist The list of tasks.
     * @param ui       The ui object that helps generate the different messgaes for display.
     * @return String The message that is to be output on the GUI.
     * @throws DukeEmptyFindException If there is empty message.
     * @throws DukeNoMatchesExcpetion If there are no matches to the input.
     */
    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeEmptyFindException, DukeNoMatchesExcpetion {
        String message = "";
        try {
            String[] findParts = input.split(" ");
            if (findParts.length == 1) {
                throw new DukeEmptyFindException(input);
            } else {
                message = ui.printKeywordTasks(findParts[1], tasklist.getTasks());
            }
        } catch (DukeEmptyFindException e) {
            message = e.getMessage();
        } catch (DukeNoMatchesExcpetion e) {
            message = e.getMessage();
        }
        assert tasklist.numOfTasks() > 0;
        return message;
    }
}
