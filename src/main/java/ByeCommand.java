/**
 * Represents a bye/exit command.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) {
        ui.format("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
