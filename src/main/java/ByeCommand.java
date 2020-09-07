/**
 * handles the "bye" commands
 */
public class ByeCommand extends Command {

    /**
     * Constructor for ByeCommand
     *
     * @param description
     * @throws IllegalArgumentException
     */
    public ByeCommand(String description) throws IllegalArgumentException {
        super(description);
    }

    /**
     * terminates the bot
     *
     * @param taskList
     */
    public String execute(TaskList taskList) {
        System.exit(0);
        return TextUi.printMessage("Bye bye! Hope to see you again soon!\n");
    }
}
