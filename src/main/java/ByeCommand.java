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
    public void execute(TaskList taskList) {
        TextUi.printMessage("Bye bye! Hope to see you again soon!\n");
        System.exit(0);
    }
}