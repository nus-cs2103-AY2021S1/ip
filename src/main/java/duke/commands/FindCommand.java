package duke.commands;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Colour;
import duke.utils.Messages;

import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandOutput executeCommand(TaskManager taskManager, Storage storage) {
        List<Task> filteredTasks = taskManager.findTasksByKeyword(keyword);
        if (filteredTasks.size() == 0) {
            return new CommandOutput(Colour.convertTextToRed(Messages.NO_TASKS_UNDER_KEYWORD_MESSAGE), false);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the matching tasks in your list:\n");
            for (Task task : filteredTasks) {
                stringBuilder.append(task.toString() + "\n");
            }
            return new CommandOutput(stringBuilder.toString(), false);
        }
    }
}
