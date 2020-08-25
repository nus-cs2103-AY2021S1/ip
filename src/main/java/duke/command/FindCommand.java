package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String arg;
    public FindCommand(String arg) {
        this.arg = arg;
    }
    
    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        List<Task> tasksItems = taskItems.getAll();
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task: tasksItems) {
            if (task.getDescription().contains(arg)) {
                matchingTasks.add(task);
            }
        }
        ui.findTaskReply(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
