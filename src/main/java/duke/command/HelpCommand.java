package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a HelpCommand.
 */
public class HelpCommand extends Command {

    /**
     * Creates a HelpCommand object.
     */
    public HelpCommand() {
        super(CommandType.HELP);
    }

    /**
     * Executes a help command.
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return List of commands the user can use.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.printHelp();
    }
}
