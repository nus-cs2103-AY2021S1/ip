package duke.command;

import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getListOfTasks()) {
            String name = task.getName();
            if (name.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        ui.displayFinding(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
