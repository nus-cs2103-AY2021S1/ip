/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) {
        return ui.displayTasksWithCommand(taskList.getTasks(), "list");
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
