/**
 * Class that makes a TagCommand which tags the different tasks.
 */
public class TagCommand extends Command {
    private String input;

    /**
     * Constructs a TagCommand object with a provided input.
     *
     * @param input The input that has been input by the user.
     */
    TagCommand(String input) {
        this.input = input;
    }

    /**
     * Tags the task.
     *
     * @param tasklist The list of tasks.
     * @param ui       The ui object that helps generate the different messgaes for display.
     * @return String The message that is to be output on the GUI.
     * @throws DukeEmptyTagException If there is an empty tag message.
     */
    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeEmptyTagException {
        String message = "";
        String[] tagCommand = this.input.split(" ");
        if (tagCommand.length == 1 || tagCommand.length == 2) {
            throw new DukeEmptyTagException(input);
        } else {
            message = ui.printTag(this.input, tasklist.getTasks());
        }
        return message;
    }
}
