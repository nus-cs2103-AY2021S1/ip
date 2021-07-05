package duke;

/**
 * Represents a command to show an invalid user input.
 */
public class InvalidCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showError("I'm sorry, but I don't know what that means :(");
    }
}
