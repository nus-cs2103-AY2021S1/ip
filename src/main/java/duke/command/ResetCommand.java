package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a ResetCommand.
 */
public class ResetCommand extends Command {

    /**
     * Creates a ResetCommand object.
     */
    public ResetCommand() {
        super(CommandType.RESET);
    }

    /**
     * Executes a reset command.
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return A message that users should not see if exceptions were handled correctly.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return "You're not supposed to see this :o";
    }
}
