package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * When users input unrecognized or invalid commands, this object is
 * created to handle it. The default response for this command is to
 * notify the user that the program does not understand the input. However,
 * this class allows for custom message to be passed to the user as well.
 */
public class InvalidCommand implements Command {

    private final String toSend;

    public InvalidCommand(String toSend) {
        this.toSend = toSend;
    }

    public InvalidCommand() {
        toSend = "Sorry I do not know what that means :(";
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendMessage(toSend);
    }
}
