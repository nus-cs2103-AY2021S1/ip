package duke;

import java.util.ArrayList;
import java.io.IOException;

/**
 * Represents a command to find tasks with matching keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a find command to find tasks.
     * 
     * @param keyword The matching keyword of the task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : taskList.getList()) {
            if (task.getDescription().contains(this.keyword)) {
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
