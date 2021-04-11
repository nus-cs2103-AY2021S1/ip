package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * This command handles invalid user commands and has the option to
 * send users a default message notifying the wrong command or send
 * user a custom message.
 */
public class InvalidCommand implements Command {

    private final String toSend;

    /**
     * Constructs the invalid command with a custom message.
     * @param toSend the custom message.
     */
    public InvalidCommand(String toSend) {
        this.toSend = toSend;
    }

    /**
     * Constructs the invalid command with a default message.
     */
    public InvalidCommand() {
        toSend = "Sorry I do not know what that means :(";
    }

    /**
     * Identifies if the command calls for an exit of the program.
     * @return the value of whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and generates the response message.
     * @param tasks the task list.
     * @param ui the ui.
     * @param storage the storage.
     * @return the response message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendMessage(toSend);
        return toSend;
    }
}
