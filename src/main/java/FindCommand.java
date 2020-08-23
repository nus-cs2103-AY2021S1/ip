import java.util.ArrayList;
import java.util.List;

/**
 * Represents a find/search command.
 */
public class FindCommand extends Command {
    private String afterCommand;

    /**
     * Constructor for the find command.
     * @param afterCommand search keyword.
     */
    public FindCommand(String afterCommand) {
        this.afterCommand = afterCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) {
        // needs an after command
        if (afterCommand == null) {
            ui.throwDukeException(new DukeException("Please enter the keyword!"));
            return;
        }

        List<Task> tempList = new ArrayList<>();
        String keyword = afterCommand;
        // collate the tasks with the keyword
        for (Task task : taskList.getTasks()) {
            if (task.getDetails().contains(keyword)) {
                tempList.add(task);
            }
        }
        // display those tasks
        ui.displayTasksWithCommand(tempList, "find");
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
