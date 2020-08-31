package duke.command;

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
        int count = 0;
        boolean hasMatching = false;
        String lowercaseString = matchString.toLowerCase();
        String outputMessage = "";
        for (Task task : store) {
            count++;
            if (task.toString().toLowerCase().contains(lowercaseString)) {
                if (!hasMatching) { // check if this is first match
                    outputMessage += ui.print("Here are the matching tasks in your list:\n");
                    hasMatching = true;
                }
                outputMessage += ui.print(String.format("%d. %s", count, task));
            }
        }
        if (!hasMatching) {
            outputMessage += ui.print(String.format("You have no tasks matching '%s'", matchString));
        }
        return outputMessage;
    }
}
