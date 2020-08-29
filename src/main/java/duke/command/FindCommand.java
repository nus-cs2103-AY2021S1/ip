package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class FindCommand extends Command {
    String searchTerms;

    public FindCommand(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] tasksFound = tasks.findTasksToString(this.searchTerms);
        if (tasksFound.length > 0) {
            String listTasksFound = String.join("\n", tasksFound);
            String successfulMsg = "Here are the matching tasks in your list:\n" + listTasksFound;
            ui.showMessage(successfulMsg);
        } else {
            String unsuccessfulMsg = "There were no matches to that term";
            ui.showMessage(unsuccessfulMsg);
        }
    }
}