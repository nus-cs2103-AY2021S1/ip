/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) {
        ui.displayTasksWithCommand(taskList.getTasks(), "list");
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
