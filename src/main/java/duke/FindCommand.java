package duke;

import java.util.ArrayList;
import java.io.IOException;

public class FindCommand extends Command {
    private String keyword;
    
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : taskList.getList()) {
            if (task.getName().contains(this.keyword)) {
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
    
    @Override
    public boolean isExit() {
        return false;
    }
}
