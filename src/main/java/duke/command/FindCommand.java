package duke.command;

import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;


public class FindCommand implements Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks, Storage store) throws DukeException {
        List<Task> results = tasks.findAll(query);
        String tasksString = "";
        if (results.size() == 0) {
            return "No matches found :(";
        } else {
            for (int i = 0; i < results.size(); i++) {
                tasksString += "" + (i + 1) + "." + results.get(i).toString() + (i + 1 == tasks.size() ? "" : "\n");
            }
            return "Here are the matching tasks in your list:\n" + tasksString;
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
