import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String afterCommand;

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
        ui.displayTasksWithKeyword(tempList);
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
