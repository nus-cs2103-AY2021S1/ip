package duke.command;

import duke.Ui;

public class ByeCommand implements Command {
    /**
     * The constructor for the ByeCommand object.
     */
    public ByeCommand() {}

    /**
     * Executes the bye command by providing a goodbye message to the user.
     * @return the message representing the goodbye message to the user.
     */
    public String execute() {
        return Ui.bye();
    }
}
