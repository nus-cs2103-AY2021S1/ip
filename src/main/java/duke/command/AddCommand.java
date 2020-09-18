package duke.command;

import duke.ImageType;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an AddCommand.
 */
public class AddCommand extends Command {

    /**
     * Creates an AddCommand object.
     */
    public AddCommand() {
        super(CommandType.ADD, ImageType.PENDING);
    }

    /**
     * Executes an add command.
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return Prompt for the user to enter a task type.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.printPrompt("What kind of task is it?\n"
                + " - Todo\n"
                + " - Deadline\n"
                + " - Event\n");
    }
}
