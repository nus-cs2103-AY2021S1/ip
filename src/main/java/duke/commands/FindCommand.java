package duke.commands;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;
import duke.utils.Colour;
import duke.utils.Ui;

import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void executeCommand(TaskManager taskManager, Ui ui, Storage storage) {
        List<Task> filteredTasks = taskManager.findTasksByKeyword(keyword);
        if (filteredTasks.size() == 0) {
            ui.print(Colour.convertTextToRed("I am sorry but there are no tasks under the keyword you have inputted."));
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the matching tasks in your list:\n");
            for (Task task : filteredTasks) {
                stringBuilder.append(task.toString() + "\n");
            }
            ui.print(stringBuilder.toString());
        }
    }
}
