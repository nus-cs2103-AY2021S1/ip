package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(query)) {
                matchedTasks.add(task);
            }
        }
        if (matchedTasks.size() == 0) {
            ui.printResponse("No task found...");
            return "No task found...";
        }
        StringBuilder taskMessage = new StringBuilder();
        taskMessage.append("Here are the matching tasks in your list:");
        taskMessage.append(TaskList.getTaskMessage(matchedTasks));
        ui.printResponse(taskMessage.toString());
        return taskMessage.toString();
    }
}
