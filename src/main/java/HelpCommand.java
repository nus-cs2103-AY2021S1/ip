/**
 * Represents a help command.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) {
        return ui.displayHelpMenu();
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
