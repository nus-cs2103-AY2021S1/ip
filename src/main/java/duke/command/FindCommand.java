package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

public class FindCommand extends Command {

    private String matchString;

    public FindCommand(String matchString) {
        this.matchString = matchString;
    }

    public String execute(TaskList taskList, Ui ui) {
        List<Task> store = taskList.getList();
        String lowercaseString = matchString.toLowerCase();
        StringBuilder outputMessage = new StringBuilder("Here are the matching tasks in your list:\n");
        List<Task> matchingTasks = new ArrayList<>();
        store.stream().filter(task -> task.toString().toLowerCase()
                .contains(lowercaseString)).forEach(task -> {
                    matchingTasks.add(task);
                    outputMessage.append(String.format("%d. %s", store.indexOf(task) + 1, task));
                });
        if (matchingTasks.isEmpty()) {
            return ui.print(String.format("You have no tasks matching '%s'", matchString));
        } else {
            return ui.print(outputMessage.toString());
        }
    }
}
