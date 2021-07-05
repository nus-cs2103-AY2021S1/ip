package duke;

import java.util.ArrayList;
import java.io.IOException;

/**
 * Represents a command to find tasks with matching keyword.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * Constructs a find command to find tasks.
     * 
     * @param input String input of the task to be found
     */
    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        assert input.length() > 4 : "Please provide keyword";
        String keyword = input.substring(5);
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : taskList.getList()) {
            if (task.getDescription().contains(keyword)) {
                list.add(task);
            }
        }
        if (list.size() == 0) {
            return "No tasks matching the keyword";
        } else {
            String matchingResults = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                matchingResults = matchingResults + ((i + 1) + ": " + list.get(i)) + "\n";
            }
            return matchingResults;
        }
    }
}
