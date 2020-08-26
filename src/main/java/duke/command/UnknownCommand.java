package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * UnknownCommand is an extension of a command.
 * It displays a message to inform the user that the input is unrecognised.
 */
public class UnknownCommand extends Command {
    public UnknownCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        Ui.addMessage("Sorry, but I don't know what that means \u2639");
        Ui.sendMessages();
    }
}
