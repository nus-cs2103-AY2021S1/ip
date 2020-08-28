package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(query)) {
                matchedTasks.add(task);
            }
        }
        if (matchedTasks.size() == 0) {
            ui.printResponse("No task found...");
            return;
        }
        StringBuilder taskMessage = new StringBuilder();
        taskMessage.append("Here are the matching tasks in your list:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            String task = String.format("\n\t%d.%s", (i + 1), matchedTasks.get(i));
            taskMessage.append(task);
        }
        ui.printResponse(taskMessage.toString());
    }
}
