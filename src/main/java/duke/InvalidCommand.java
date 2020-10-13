package duke;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(Command.INVALID);
    }

    /**
     * Informs the User that the Command is invalid.
     *
     * @return String message informing the user.
     */
    public String execute() {
        return Ui.informInvalidCommand();
    }
}
