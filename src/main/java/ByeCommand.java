/**
 * Represents a bye/exit command.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) {
        return ui.format("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
