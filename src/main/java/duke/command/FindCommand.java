package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.List;

public class FindCommand extends Command {

    private String matchString;

    public FindCommand(String matchString) {
        this.matchString = matchString;
    }

    public void execute(TaskList taskList, Ui ui) {
        List<Task> store = taskList.getList();
        int count = 0;
        boolean hasMatching = false;
        String lowercaseString = matchString.toLowerCase();
        for (Task task : store) {
            count++;
            if (task.toString().toLowerCase().contains(lowercaseString)) {
                if (!hasMatching) { // check if this is first match
                    ui.print("Here are the matching tasks in your list:");
                    hasMatching = true;
                }
                ui.print(String.format("%d. %s", count, task));
            }
        }
        if (!hasMatching) {
            ui.print(String.format("You have no tasks matching '%s'", matchString));
        }
    }
}
