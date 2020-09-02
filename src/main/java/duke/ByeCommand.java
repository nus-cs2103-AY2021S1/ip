package duke;

/**
 * Represents a bye command.
 */
public class ByeCommand extends Command {

    /**
     * Terminates execution of bot.
     * @param taskList Task list containing tasks.
     * @param storage Storage for storing and retrieving all tasks.
     * @param ui Handles printing of user interaction.
     * @return Text when ByeCommand is executed.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.displayBye();
    }
}
