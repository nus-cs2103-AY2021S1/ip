package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents an ExitCommand.
 */
public class ExitCommand extends Command {

    /**
     * Creates an ExitCommand object.
     */
    public ExitCommand() {
        super(CommandType.EXIT);
    }

    /**
     * Executes an exit command.
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return Goodbye message to the user.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.printGoodbyeMessage();
    }
}
