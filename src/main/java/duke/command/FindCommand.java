package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;

public class FindCommand implements Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        List<Task> results = tasks.findAll(query);
        String tasksString = "";
        if (results.size() == 0) {
            ui.print("No matches found :(");
        } else {
            for (int i = 0; i < results.size(); i++) {
                tasksString += "" + (i + 1) + "." + results.get(i).toString() + (i + 1 == tasks.size() ? "" : "\n");
            }
            ui.print("Here are the matching tasks in your list:\n" + tasksString);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
