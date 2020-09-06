import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) {
        // needs an after command
        if (afterCommand == null) {
            return ui.throwDukeException(new DukeException("Please enter the keyword!"));
        }

        List<Task> tempList = new ArrayList<>();
        String keyword = afterCommand;
        // collate the tasks with the keyword (using streams and lambda)
        tempList.addAll(taskList.getTasks().stream()
                .filter(task -> task.getDetails().contains(keyword))
                .collect(Collectors.toList()));
        // display those tasks
        return ui.displayTasksWithCommand(tempList, "find");
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
